package model;

import java.time.LocalDateTime;

public class Session {
    private String sessionId;
    private String topic;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Speaker speaker; // Add Speaker attribute

    public Session(String sessionId, String topic, LocalDateTime startTime, LocalDateTime endTime) {
        this.sessionId = sessionId;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Additional constructor to set the Speaker
    public Session(String sessionId, String topic, LocalDateTime startTime, LocalDateTime endTime, Speaker speaker) {
        this.sessionId = sessionId;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
    }

    // Getters
    public String getSessionId() {
        return sessionId;
    }

    public String getTopic() {
        return topic;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    // Setter for Speaker in case you want to set it after construction
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }
}
