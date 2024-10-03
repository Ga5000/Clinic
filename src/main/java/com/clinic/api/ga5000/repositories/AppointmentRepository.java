package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.Appointment;
import com.clinic.api.ga5000.entities.enums.Speciality;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' " +
            "WHERE a.appointmentId = :appointmentId " +
            "AND (:ssn IS NULL OR a.patient.ssn = :ssn) " +
            "AND (:medicalLicense IS NULL OR a.doctor.medicalLicense = :medicalLicense)")
    void cancelAppointment(
            @Param("appointmentId") UUID appointmentId,
            @Param("ssn") String ssn,
            @Param("medicalLicense") String medicalLicense);

    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status = 'CANCELED' WHERE a.appointmentDate = :date AND a.doctor.medicalLicense = :medicalLicense")
    void cancelAllAppointmentsOfADay(@Param("date") LocalDate date, @Param("medicalLicense") String medicalLicense); //doctor

    @Query("SELECT a.appointmentId as id, a.appointmentDate as date, a.appointmentTime as time, a.fee as fee, " +
            "concat(a.doctor.firstName, ' ', a.doctor.lastName) as doctorName, a.doctor.speciality as speciality, a.status as status " +
            "FROM Appointment a " +
            "WHERE (:ssn IS NULL OR a.patient.ssn = :ssn) " +
            "AND (:medicalLicense IS NULL OR a.doctor.medicalLicense = :medicalLicense) " +
            "AND (:filterDate IS NULL OR a.appointmentDate = :filterDate) " +
            "AND (:speciality IS NULL OR a.doctor.speciality = :speciality) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR a.appointmentDate BETWEEN :startDate AND :endDate)")
    Set<Tuple> findAppointments(
            @Param("ssn") String ssn,
            @Param("medicalLicense") String medicalLicense,
            @Param("filterDate") LocalDate filterDate,
            @Param("speciality") Speciality speciality,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


    List<Appointment> findAppointmentByAppointmentDate(LocalDate date);

    Set<Appointment> findAppointmentByAppointmentDateAndDoctorMedicalLicense(LocalDate appointmentDate, String doctor_medicalLicense);
}
