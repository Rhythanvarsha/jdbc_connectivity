package model;

public class Attendee {
    private String attendeeId;
    private String name;

    public Attendee(String attendeeId, String name) {
        this.attendeeId = attendeeId;
        this.name = name;
    }

    // Getters
    public String getAttendeeId() {
        return attendeeId;
    }

    public String getName() {
        return name;
    }
}
