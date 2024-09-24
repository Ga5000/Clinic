package com.ga5000.Clinic.services.interfaces;

import java.time.LocalDate;

public interface NotificationService {
    void sendNotification(String ssn);
    void sendReminderNotification(LocalDate date);
}
