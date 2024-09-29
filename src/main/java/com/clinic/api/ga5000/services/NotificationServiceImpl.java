package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.entities.Appointment;
import com.clinic.api.ga5000.entities.Notification;
import com.clinic.api.ga5000.entities.enums.NotificationType;
import com.clinic.api.ga5000.repositories.NotificationRepository;
import com.clinic.api.ga5000.services.interfaces.NotificationService;
import com.clinic.api.ga5000.utils.DtoConverter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public NotificationServiceImpl(NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void sendReminderNotification(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
    }

    @Override
    public void sendCancelationNotification(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
    }

    @Override
    public void sendConfirmationNotification(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Appointment made");
        message.setTo(appointment.getPatient().getEmail());

        String appointmentDetails = String.format(
                """
                        Appointment has been made
                        Appointment details: %s
                        Date: %s
                        Time: %s
                        Fee: %.2f""",
                DtoConverter.convertToDoctorDTO(appointment.getDoctor()),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getFee()
        );

        message.setText(appointmentDetails);
        message.setFrom("gbr.lisboa@gmail.com");
        mailSender.send(message);

        Notification notification = new Notification(
                message.getText(),
                appointment.getPatient(),
                LocalDateTime.now(),
                NotificationType.APPOINTMENT_CONFIRMATION
        );

        notificationRepository.save(notification);
    }


}

