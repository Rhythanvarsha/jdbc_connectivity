package service;

import database.DatabaseConnection;
import model.Participant;
import model.Question;
import model.Quiz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuizServiceImpl implements QuizService {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void registerParticipant(Participant participant) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Participants (name, email) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, participant.getName());
            
            statement.setString(2, participant.getEmail());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                participant.setParticipantId(keys.getInt(1));
            }
            System.out.println("Participant registered successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addQuiz(Quiz quiz) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Quiz (title, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, quiz.getTitle());
            statement.setString(2, quiz.getDescription());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                quiz.setQuizId(keys.getInt(1));
            }
            System.out.println("Quiz added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addQuestion(Question question) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Questions ( question_text, question_type, correct_answer) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setInt(1, question.getQuizId());
            statement.setString(1, question.getQuestionText());
            statement.setString(2, question.getQuestionType());
            statement.setString(3, question.getCorrectAnswer());
            statement.executeUpdate();
            System.out.println("Question added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void takeQuiz(Participant participant, int quizId) {
        executorService.submit(() -> {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String fetchQuestionsSql = "SELECT question_id, question_text, correct_answer FROM Questions WHERE quiz_id = ?";
                PreparedStatement fetchQuestionsStatement = connection.prepareStatement(fetchQuestionsSql);
                fetchQuestionsStatement.setInt(1, quizId);
                ResultSet resultSet = fetchQuestionsStatement.executeQuery();

                if (!resultSet.isBeforeFirst()) {
                    System.out.println("No questions found for this quiz.");
                    return;
                }

                int score = 0;
                Scanner scanner = new Scanner(System.in);

                System.out.println("Starting Quiz...");
                while (resultSet.next()) {
                    int questionId = resultSet.getInt("question_id");
                    String questionText = resultSet.getString("question_text");
                    String correctAnswer = resultSet.getString("correct_answer");

                    System.out.println(questionText);
                    System.out.print("Your answer: ");
                    String userAnswer = scanner.nextLine();

                    if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                        score++;
                    } else if (userAnswer.isEmpty()) {
                        System.out.println("Invalid answer. Please answer each question.");
                        continue;
                    }
                }

                String insertResultSql = "INSERT INTO QuizResults (participant_id, quiz_id, score) VALUES (?, ?, ?)";
                PreparedStatement insertResultStatement = connection.prepareStatement(insertResultSql);
                insertResultStatement.setInt(1, participant.getParticipantId());
                insertResultStatement.setInt(2, quizId);
                insertResultStatement.setInt(3, score);
                insertResultStatement.executeUpdate();

                System.out.println("Quiz completed. Your score: " + score);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void viewScore(Participant participant) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT Quiz.title, QuizResults.score FROM QuizResults INNER JOIN Quiz ON QuizResults.quiz_id = Quiz.quiz_id WHERE participant_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, participant.getParticipantId());
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Quiz Results:");
            while (resultSet.next()) {
                String quizTitle = resultSet.getString("title");
                int score = resultSet.getInt("score");
                System.out.println("Quiz: " + quizTitle + " | Score: " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveQuizDataToFile(int quizId) throws IOException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String fetchQuizSql = "SELECT title, description FROM Quiz WHERE quiz_id = ?";
            PreparedStatement fetchQuizStatement = connection.prepareStatement(fetchQuizSql);
            fetchQuizStatement.setInt(1, quizId);
            ResultSet quizResultSet = fetchQuizStatement.executeQuery();

            if (!quizResultSet.next()) {
                System.out.println("Quiz not found.");
                return;
            }

            String title = quizResultSet.getString("title");
            String description = quizResultSet.getString("description");

            String fetchQuestionsSql = "SELECT question_text FROM Questions WHERE quiz_id = ?";
            PreparedStatement fetchQuestionsStatement = connection.prepareStatement(fetchQuestionsSql);
            fetchQuestionsStatement.setInt(1, quizId);
            ResultSet questionsResultSet = fetchQuestionsStatement.executeQuery();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Quiz_" + quizId + "_Data.txt"))) {
                writer.write("Quiz Title: " + title);
                writer.newLine();
                writer.write("Description: " + description);
                writer.newLine();
                writer.write("Questions:");
                writer.newLine();

                while (questionsResultSet.next()) {
                    writer.write("- " + questionsResultSet.getString("question_text"));
                    writer.newLine();
                }
            }

            System.out.println("Quiz data saved to file.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
