-- SQL for creating the database and tables
CREATE DATABASE QuizApp;

USE QuizApp;

CREATE TABLE Participants (
    participant_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Quiz (
    quiz_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    description TEXT
);

CREATE TABLE Questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    quiz_id INT,
    question_text TEXT,
    question_type VARCHAR(20),
    correct_answer VARCHAR(255),
    FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id)
);

CREATE TABLE Options (
    option_id INT PRIMARY KEY AUTO_INCREMENT,
    question_id INT,
    option_text VARCHAR(255),
    is_correct BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES Questions(question_id)
);

CREATE TABLE QuizResults (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    participant_id INT,
    quiz_id INT,
    score INT,
    date_taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (participant_id) REFERENCES Participants(participant_id),
    FOREIGN KEY (quiz_id) REFERENCES Quiz(quiz_id)
);
select*from QuizResults;
select*from Questions;
select*from Participants;
select*from Quiz;
