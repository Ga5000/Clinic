package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
