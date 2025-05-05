package model;

public class Question {
    private int id;
    private int quizId;
    private String text;
    private String questionType;
    private String correctAnswer;

    // Constructor
    public Question(int quizId2, String text, String questionType, String correctAnswer) {
        this.quizId = quizId2;
        this.text = text;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for quizId
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    // Getter and setter for question text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Additional getter method for question text, as requested
    public String getQuestionText() {
        return text;
    }

    // Getter and setter for question type
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    // Getter and setter for correct answer
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    // toString method for displaying question details
    @Override
    public String toString() {
        return "Question ID: " + id + "\nQuiz ID: " + quizId + "\nText: " + text + "\nType: " + questionType;
    }
}
