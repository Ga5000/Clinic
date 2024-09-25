package com.ga5000.Clinic.services;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Notification;
import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.NotificationStatus;
import com.ga5000.Clinic.entities.enums.NotificationType;
import com.ga5000.Clinic.repositories.NotificationRepository;
import com.ga5000.Clinic.services.interfaces.NotificationService;
import com.ga5000.Clinic.utils.Finder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public void sendNotification(String ssn, Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        Patient patient = finder.findAndReturnPatientBySsn(ssn);

        message.setTo(patient.getEmail());
        message.setSubject("Appointment booked");
        message.setText("Appointment details:\n\n" +
                "Doctor: " + appointment.getDoctor().getName() + "\n" +
                "Speciality: " + appointment.getDoctor().getSpeciality().toString() + "\n" +
                "Appointment Date: " + appointment.getDate().toString() + "\n" +
                "Appointment Time: " + appointment.getTime().toString() + "\n" +
                "Fee: " + appointment.getFee());

        message.setFrom("example@gmail.com");
        mailSender.send(message);


        Notification notification = new Notification(message.getText(), patient, NotificationStatus.UNREAD,
                NotificationType.GENERAL_INFO, LocalDateTime.now());

        notificationRepository.save(notification);

    }

    @Override
    @Scheduled(cron = "0 0 18 * * ?")
    public void sendReminderNotification() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Patient> recipients = notificationRepository.findPatientsWithAppointmentsOneDayPrior(tomorrow);
        if(recipients.isEmpty()){
            throw new RuntimeException("No patients found");
        }

        for (Patient patient : recipients) {
            for (Appointment appointment : patient.getAppointments()) {

                if (appointment.getDate().equals(tomorrow)) {
                    SimpleMailMessage message = getSimpleMailMessage(patient, appointment);

                    mailSender.send(message);

                }
            }
        }
    }

    private static SimpleMailMessage getSimpleMailMessage(Patient patient, Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(patient.getEmail());
        message.setSubject("Appointment Reminder");


        String doctorName = appointment.getDoctor().getName();

        message.setText("You have an appointment tomorrow:\n\n" +
                "Doctor: " + doctorName + "\n" +
                "Time: " + appointment.getTime().toString() + "\n");

        message.setFrom("example@gmail.com");
        return message;
    }

}
