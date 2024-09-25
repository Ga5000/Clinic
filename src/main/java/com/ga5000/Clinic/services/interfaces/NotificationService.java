package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.entities.Appointment;

import java.time.LocalDate;

public interface NotificationService {
    void sendNotification(String ssn, Appointment appointment);
    void sendReminderNotification(LocalDate tomorrow);
}
