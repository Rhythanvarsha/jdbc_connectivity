package service;



import model.Attendee;
import model.Session;
import model.Speaker;
import java.time.LocalDateTime;

public interface EventScheduler {
    void addSession(Session session) throws exception.ScheduleConflictException;
    void assignSpeaker(Speaker speaker, String sessionId) throws exception.ScheduleConflictException, Exception;
    boolean checkAvailability(String speakerId, LocalDateTime startTime, LocalDateTime endTime);
    void registerAttendee(Attendee attendee, String sessionId);
    void sendNotification(String message, String attendeeId);
}
