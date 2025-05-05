package model;

public class Speaker {
    private String speakerId;
    private String name;

    public Speaker(String speakerId, String name) {
        this.speakerId = speakerId;
        this.name = name;
    }

    // Getters
    public String getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }
}
