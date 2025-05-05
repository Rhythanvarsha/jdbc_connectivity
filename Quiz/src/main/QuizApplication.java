
package main;

import model.Participant;
import model.Question;
import service.QuizService;
import service.QuizServiceImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QuizApplication {

    public static void main(String[] args) {
        QuizService quizService = new QuizServiceImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------Online Quiz Application---------");

        while (true) {
            System.out.println("1. Register Participant");
            System.out.println("2. Add Quiz");
            System.out.println("3. Add Question");
            System.out.println("4. Take Quiz");
            System.out.println("5. View Score");
            System.out.println("6. Save Quiz Data to File");
            System.out.println("7. Exit");

            int choice = 0;

            // Wrap nextInt() in a try-catch to handle non-integer input
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop to prompt again
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter participant name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter participant email: ");
                    String email = scanner.nextLine();
                    Participant participant = new Participant(name, email);
                    quizService.registerParticipant(participant);
                    break;

                case 2:
                    System.out.print("Enter quiz title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter quiz description: ");
                    String description = scanner.nextLine();
                    quizService.addQuiz(new model.Quiz(title, description));
                    break;

                case 3:
                    System.out.print("Enter quiz ID for the question: ");
                    int quizId = 0;
                    try {
                        quizId = scanner.nextInt();
                        scanner.nextLine(); 
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid quiz ID.");
                        scanner.nextLine();
                        continue; 
                    }
                    System.out.print("Enter question text: ");
                    String questionText = scanner.nextLine();
                    System.out.print("Enter correct answer: ");
                    String correctAnswer = scanner.nextLine();
//                    quizService.addQuestion(new model.Question(quizId, questionText, "MultipleChoice", correctAnswer));
                 // Example of creating a Question object with quizId, questionText, questionType, and correctAnswer
                    quizService.addQuestion(new Question(quizId, questionText, "MultipleChoice", correctAnswer));

                    break;

                case 4:
                    System.out.print("Enter your participant ID: ");
                    int participantId = 0;
                    try {
                        participantId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid participant ID.");
                        scanner.nextLine(); // Clear the invalid input
                        continue; // Restart to prompt for correct input
                    }
                    System.out.print("Enter quiz ID to take: ");
                    try {
                        quizId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid quiz ID.");
                        scanner.nextLine(); // Clear the invalid input
                        continue; // Restart to prompt for correct input
                    }
                    quizService.takeQuiz(new Participant(participantId, ""), quizId);
                    break;

                case 5:
                    System.out.print("Enter your participant ID to view score: ");
                    try {
                        participantId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid participant ID.");
                        scanner.nextLine(); // Clear the invalid input
                        continue; // Restart to prompt for correct input
                    }
                    quizService.viewScore(new Participant(participantId, ""));
                    break;

                case 6:
                    System.out.print("Enter quiz ID to save data to file: ");
                    try {
                        quizId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid quiz ID.");
                        scanner.nextLine(); 
                        continue; 
                    }
                    try {
                        quizService.saveQuizDataToFile(quizId);
                    } catch (IOException e) {
                        System.out.println("Error saving quiz data to file: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Exiting application...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number from 1 to 7.");
            }
        }
    }}