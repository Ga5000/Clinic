package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.entities.Appointment;
import com.clinic.api.ga5000.entities.Notification;
import com.clinic.api.ga5000.entities.enums.NotificationType;
import com.clinic.api.ga5000.repositories.AppointmentRepository;
import com.clinic.api.ga5000.repositories.NotificationRepository;
import com.clinic.api.ga5000.services.interfaces.NotificationService;
import com.clinic.api.ga5000.utils.DtoConverter;
import jakarta.persistence.Tuple;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final AppointmentRepository appointmentRepository;
    private final JavaMailSender mailSender;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   AppointmentRepository appointmentRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.appointmentRepository = appointmentRepository;
        this.mailSender = mailSender;
    }

    @Override
    @Scheduled(cron = "0 0 18 * * *")
    public void sendReminderNotification() {
        List<Appointment> appointments = appointmentRepository.findAppointmentByAppointmentDate(LocalDate.now()
                                                                                    .plusDays(1));

        for (Appointment a : appointments) {
            String appointmentDetails = String.format(
                    """
                           Don't forget your appointment tomorrow!
                           Appointment details:
                           Doctor: %s
                           Date: %s
                           Time: %s
                           Fee: %.2f""",
                    DtoConverter.convertToDoctorDTO(a.getDoctor()),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getFee()
            );
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(a.getPatient().getEmail());
            message.setSubject("Appointment Reminder");
            message.setText(appointmentDetails);
            mailSender.send(message);
            Notification notification = new Notification(
                    message.getText(),
                    a.getPatient(),
                    LocalDateTime.now(),
                    NotificationType.REMINDER);

            notificationRepository.save(notification);
        }
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

