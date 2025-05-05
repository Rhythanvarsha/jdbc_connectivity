package service;

import model.Attendee;
import model.Session;
import model.Speaker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class EventSchedulerImpl implements EventScheduler {

    private HashMap<String, Session> sessions = new HashMap<>();
    private HashMap<String, Speaker> speakers = new HashMap<>();
    private HashMap<String, ArrayList<Attendee>> sessionAttendees = new HashMap<>();

    @Override
    public void addSession(Session session) throws exception.ScheduleConflictException {
        for (Session existingSession : sessions.values()) {
            if (session.getStartTime().isBefore(existingSession.getEndTime()) && session.getEndTime().isAfter(existingSession.getStartTime())) {
                throw new exception.ScheduleConflictException("Session conflict with existing session: " + existingSession.getSessionId());
            }
        }
        sessions.put(session.getSessionId(), session);
    }

    @Override
    public void assignSpeaker(Speaker speaker, String sessionId) throws Exception {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new Exception("Session ID not found: " + sessionId);
        }
        speakers.put(speaker.getSpeakerId(), speaker);
    }

    @Override
    public boolean checkAvailability(String speakerId, LocalDateTime startTime, LocalDateTime endTime) {
        for (Session session : sessions.values()) {
            if (session.getStartTime().isBefore(endTime) && session.getEndTime().isAfter(startTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void registerAttendee(Attendee attendee, String sessionId) {
        sessionAttendees.computeIfAbsent(sessionId, k -> new ArrayList<>()).add(attendee);
    }

    @Override
    public void sendNotification(String message, String attendeeId) {
        System.out.println("Notification to Attendee ID " + attendeeId + ": " + message);
    }
}
