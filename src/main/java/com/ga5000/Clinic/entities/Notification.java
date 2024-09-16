package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.NotificationStatus;
import com.ga5000.Clinic.entities.enums.NotificationType;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private Patient recipient;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;


    public Notification() {}

    public Notification(String message, Patient recipient, NotificationStatus status, NotificationType type,
                        LocalDateTime timestamp) {
        this.message = message;
        this.recipient = recipient;
        this.status = status;
        this.type = type;
        this.timestamp = timestamp;
    }


    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Patient recipient) {
        this.recipient = recipient;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
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
