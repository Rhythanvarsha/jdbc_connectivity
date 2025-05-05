# Quiz Application

This is a Java-based Quiz Application that allows participants to register, take quizzes, view their scores, and save quiz data to files. The application uses JDBC for database connectivity and follows a modular structure.

## Features

- **Participant Management**: Register participants with their details.
- **Quiz Management**: Add quizzes with titles and descriptions.
- **Question Management**: Add questions to quizzes with multiple-choice options.
- **Quiz Taking**: Participants can take quizzes and receive scores.
- **Score Viewing**: Participants can view their scores for completed quizzes.
- **Data Export**: Save quiz data (title, description, and questions) to a file.

## Project Structure




## Database Schema

The database schema is defined in the [`quizSQL.sql`](resources/quizSQL.sql) file. It includes the following tables:

- `Participants`: Stores participant details.
- `Quiz`: Stores quiz details.
- `Questions`: Stores questions for each quiz.
- `Options`: Stores multiple-choice options for questions.
- `QuizResults`: Stores quiz results for participants.

## Prerequisites

- Java 17 or higher
- MySQL or compatible database
- JDBC driver for MySQL
- Eclipse IDE or any Java IDE

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd Quiz



