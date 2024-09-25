package com.ga5000.Clinic.services.interfaces;

import com.ga5000.Clinic.entities.Appointment;


public interface NotificationService {
    void sendNotification(String ssn, Appointment appointment);
    void sendReminderNotification();
}
