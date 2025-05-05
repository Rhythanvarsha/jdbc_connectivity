package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import exception.ScheduleConflictException;
import model.Attendee;
import model.Session;
import model.Speaker;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        service.EventScheduler scheduler = new service.EventSchedulerImpl();

        // Define the correct formatter without seconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm]");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Event Scheduler Menu ===");
            System.out.println("1. Add a new session");
            System.out.println("2. Assign a speaker to a session");
            System.out.println("3. Register an attendee for a session");
            System.out.println("4. Send notification to attendee");
            System.out.println("5. Check speaker availability");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter session ID: ");
                    String sessionId = scanner.nextLine();
                    System.out.print("Enter session topic: ");
                    String topic = scanner.nextLine();
                    System.out.print("Enter start time (YYYY-MM-DD[ T]HH:MM): ");
                    String startTimeInput = scanner.nextLine();
                    LocalDateTime startTime = LocalDateTime.parse(startTimeInput, formatter);
                    System.out.print("Enter end time (YYYY-MM-DD[ T]HH:MM): ");
                    String endTimeInput = scanner.nextLine();
                    LocalDateTime endTime = LocalDateTime.parse(endTimeInput, formatter);

                    try {
                        Session session = new Session(sessionId, topic, startTime, endTime);
                        scheduler.addSession(session);
                        System.out.println("Session added successfully.");
                    } catch (ScheduleConflictException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter speaker ID: ");
                    String speakerId = scanner.nextLine();
                    System.out.print("Enter speaker name: ");
                    String speakerName = scanner.nextLine();
                    System.out.print("Enter session ID for this speaker: ");
                    String sessionToAssign = scanner.nextLine();

                    try {
                        Speaker speaker = new Speaker(speakerId, speakerName);
                        scheduler.assignSpeaker(speaker, sessionToAssign);
                        System.out.println("Speaker assigned successfully.");
                    } catch (ScheduleConflictException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter attendee ID: ");
                    String attendeeId = scanner.nextLine();
                    System.out.print("Enter attendee name: ");
                    String attendeeName = scanner.nextLine();
                    System.out.print("Enter session ID for this attendee: ");
                    String sessionForAttendee = scanner.nextLine();

                    Attendee attendee = new Attendee(attendeeId, attendeeName);
                    scheduler.registerAttendee(attendee, sessionForAttendee);
                    System.out.println("Attendee registered successfully.");
                    break;

                case 4:
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();
                    System.out.print("Enter attendee ID to notify: ");
                    String notifyAttendeeId = scanner.nextLine();

                    scheduler.sendNotification(message, notifyAttendeeId);
                    System.out.println("Notification sent successfully.");
                    break;

                case 5:
                    System.out.print("Enter speaker ID: ");
                    String speakerToCheck = scanner.nextLine();
                    System.out.print("Enter start time (YYYY-MM-DD[ T]HH:MM): ");
                    String checkStartInput = scanner.nextLine();
                    LocalDateTime checkStart = LocalDateTime.parse(checkStartInput, formatter);
                    System.out.print("Enter end time (YYYY-MM-DD[ T]HH:MM): ");
                    String checkEndInput = scanner.nextLine();
                    LocalDateTime checkEnd = LocalDateTime.parse(checkEndInput, formatter);

                    boolean available = scheduler.checkAvailability(speakerToCheck, checkStart, checkEnd);
                    if (available) {
                        System.out.println("Speaker is available for the specified time.");
                    } else {
                        System.out.println("Speaker is not available for the specified time.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
