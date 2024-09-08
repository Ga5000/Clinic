package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Patient;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT a.appointmentId AS appointmentId, a.appointmentDate AS appointmentDate, " +
            "a.appointmentTime AS appointmentTime, a.speciality AS speciality, s.name AS doctorName " +
            "FROM Appointment a JOIN a.doctor s " +
            "WHERE a.patient.patientId = :patientId AND a.status = 'SCHEDULED' " +
            "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Tuple> findScheduledAppointments(@Param("patientId") Long patientId);

    @Query("SELECT a.appointmentId AS appointmentId, a.appointmentDate AS appointmentDate, " +
            "a.appointmentTime AS appointmentTime, a.speciality AS speciality, s.name AS doctorName " +
            "FROM Appointment a JOIN a.doctor s " +
            "WHERE a.patient.patientId = :patientId AND a.status = 'COMPLETED' " +
            "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Tuple> findAppointmentHistory(@Param("patientId") Long patientId);

    @Query("SELECT a.appointmentId AS appointmentId, a.appointmentDate AS appointmentDate, " +
            "a.appointmentTime AS appointmentTime, a.speciality AS speciality, s.name AS doctorName " +
            "FROM Appointment a JOIN a.doctor s " +
            "WHERE a.patient.patientId = :patientId AND a.status = 'CANCELED' " +
            "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Tuple> findCanceledAppointments(@Param("patientId") Long patientId);

    @Query("SELECT a.appointmentId AS appointmentId, a.appointmentDate AS appointmentDate, " +
            "a.appointmentTime AS appointmentTime, a.speciality AS speciality, s.name AS doctorName, a.status AS appointmentStatus " +
            "FROM Appointment a " +
            "JOIN a.doctor s " +
            "WHERE a.patient.patientId = :patientId " +
            "ORDER BY a.appointmentDate ASC, a.appointmentTime ASC")
    List<Tuple> findAllAppointments(@Param("patientId") Long patientId);


    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' " +
            "WHERE a.patient.patientId = :patientId AND a.appointmentId = :appointmentId AND a.status = 'SCHEDULED'")
    void cancelAppointment(@Param("patientId") Long patientId, @Param("appointmentId") Long appointmentId);

    @Query("SELECT p.patientId AS patientId, p.name AS name, p.age AS age, p.birthDate AS birthDate, " +
            "p.gender AS gender, p.email AS email, p.phoneNumber AS phoneNumber, p.ageGroup AS ageGroup " +
            "FROM Patient p WHERE p.patientId = :patientId")
    Tuple findPatientInfoById(@Param("patientId") Long patientId);

    Optional<Patient> findByEmail(String email);
}
