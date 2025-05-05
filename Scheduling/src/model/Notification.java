package model;

public class Notification {
    private String message;
    private Attendee attendee;

    public Notification(String message, Attendee attendee) {
        this.message = message;
        this.attendee = attendee;
    }

    public String getMessage() {
        return message;
    }

    public Attendee getAttendee() {
        return attendee;
    }
}
