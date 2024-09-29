package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
