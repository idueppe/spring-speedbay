package io.crowdcode.speedbay.auction.model;

import java.time.LocalDateTime;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class Message {

    private String message;
    private String createdBy;
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Message withMessage(final String message) {
        this.message = message;
        return this;
    }

    public Message withCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Message withCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
