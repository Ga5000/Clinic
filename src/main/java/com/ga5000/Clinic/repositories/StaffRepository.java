package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Staff;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT a.appointmentId AS appointmentId, p.name AS patientName, p.age AS patientAge, p.birthDate AS patientBirthDate, " +
            "p.gender AS patientGender, p.ageGroup AS patientAgeGroup, a.appointmentDate AS appointmentDate, a.appointmentTime AS appointmentTime " +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor s " +
            "WHERE s.staffId = :doctorId " +
            "AND a.appointmentDate = CURRENT_DATE " +
            "AND a.status = 'SCHEDULED' " +
            "ORDER BY a.appointmentTime ASC")
    List<Tuple> findAppointmentsOfTheDay(@Param("doctorId") Long doctorId);

    @Modifying
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' " +
            "WHERE a.doctor.staffId = :doctorId AND a.appointmentId = :appointmentId AND a.status = 'SCHEDULED'")
    void cancelAppointment(@Param("appointmentId") Long appointmentId, @Param("doctorId") Long doctorId);

    @Modifying
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' " +
            "WHERE a.doctor.staffId = :doctorId AND a.status = 'SCHEDULED'")
    void cancelAllAppointments(@Param("doctorId") Long doctorId);

    @Query("SELECT a.appointmentId AS appointmentId, p.name AS patientName, p.age AS patientAge, p.birthDate AS patientBirthDate, " +
            "p.gender AS patientGender, p.ageGroup AS patientAgeGroup, a.appointmentTime AS appointmentTime " +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor s " +
            "WHERE s.staffId = :doctorId " +
            "AND a.appointmentDate BETWEEN :startDate AND :endDate " +
            "AND a.status = 'SCHEDULED' " +
            "AND (a.appointmentDate > :startDate " +
            "OR (a.appointmentDate = :startDate AND a.appointmentTime > CURRENT_TIME))")
    List<Tuple> findUpcomingAppointments(@Param("doctorId") Long doctorId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
