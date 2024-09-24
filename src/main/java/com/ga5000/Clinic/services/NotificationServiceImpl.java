package com.ga5000.Clinic.services;

import com.ga5000.Clinic.entities.Notification;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.repositories.NotificationRepository;
import com.ga5000.Clinic.services.interfaces.NotificationService;
import com.ga5000.Clinic.utils.Finder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;
    private final Finder finder;

    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender mailSender, Finder finder) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
        this.finder = finder;
    }

    @Override
    public void sendNotification(String ssn) {
        SimpleMailMessage message = new SimpleMailMessage();
        Patient patient = finder.findAndReturnPatientBySsn(ssn);
        Notification notification = new Notification();
        notification.setRecipient(patient);
        message.setTo(patient.getEmail());
        message.setSubject("Appointment booked");
    }

    @Override
    public void sendReminderNotification(LocalDate date) {

    }
}
