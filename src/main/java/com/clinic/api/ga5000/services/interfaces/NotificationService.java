package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.entities.Appointment;

public interface NotificationService {
    void sendReminderNotification();
    void sendCancelationNotification(Appointment appointment);
    void sendConfirmationNotification(Appointment appointment);
}
