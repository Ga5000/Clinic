package com.clinic.api.ga5000.entities;

import com.clinic.api.ga5000.entities.enums.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID notificationId;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "ssn", referencedColumnName = "ssn", nullable = false)
    private Patient recipient;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    public Notification(String message, Patient recipient, LocalDateTime timestamp,
                        NotificationType type) {
        this.message = message;
        this.recipient = recipient;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Notification() {}

    public UUID getNotificationId() {
        return notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Patient getRecipient() {
        return recipient;
    }

    public void setRecipient(Patient recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
