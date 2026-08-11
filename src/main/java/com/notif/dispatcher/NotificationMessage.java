package com.notif.dispatcher;

import java.io.Serializable;

// Implements Serializable because Spring's default converter uses Java
// serialization unless we configure JSON (we'll switch to JSON in a later step).
public class NotificationMessage implements Serializable {
    private String jobId;
    private String email;
    private int attempt;

    public NotificationMessage() {}

    public NotificationMessage(String jobId, String email, int attempt) {
        this.jobId = jobId;
        this.email = email;
        this.attempt = attempt;
    }

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public int getAttempt() { return attempt; }
    public void setAttempt(int attempt) { this.attempt = attempt; }

    @Override
    public String toString() {
        return "NotificationMessage{jobId=" + jobId + ", email=" + email + ", attempt=" + attempt + "}";
    }
}