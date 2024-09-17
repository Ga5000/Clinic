package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Patient;
import com.ga5000.Clinic.entities.enums.Speciality;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {

    Optional<Patient> findBySsn(String ssn);

    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, d.name as doctorName, d.speciality as doctorSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn")
    List<Tuple> findMyAppointments(String ssn);

    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, a.doctor.name as doctorName, a.doctor.speciality as appointmentSpeciality " +
            "FROM Appointment a " +
            "WHERE a.patient.ssn = :ssn AND a.date = :filterDate")
    List<Tuple> findAppointmentHistoryFilteredByDate(String ssn, LocalDate filterDate);

    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, d.name as doctorName, d.speciality as appointmentSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn AND d.speciality = :speciality")
    List<Tuple> findAppointmentsFilteredBySpeciality(String ssn, Speciality speciality);

    Optional<List<Patient>> findByName(String name);

    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee, " +
            "a.status as appointmentStatus, d.name as doctorName, d.speciality as doctorSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patient.ssn = :ssn AND a.date BETWEEN :startDate AND :endDate")
    List<Tuple> findAppointmentsWithinDateRange(String ssn, LocalDate startDate, LocalDate endDate);
}
