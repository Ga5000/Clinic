package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Patient;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,String> {

    Optional<Patient> findBySsn(String ssn);

    @Query("SELECT a.date as appointmentDate, a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, d.name as doctorName, d.speciality as doctorSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patientId = :ssn")
    List<Tuple> findMyAppointments(String ssn);

    @Query("SELECT a.date as appointmentDate a.time as appointmentTime, a.fee as appointmentFee," +
            " a.status as appointmentStatus, d.name as doctorName, d.speciality as appointmentSpeciality " +
            "FROM Appointment a JOIN a.doctor d " +
            "WHERE a.patientId = :ssn AND a.date = :filterDate")
    List<Tuple> findAppointmentHistoryFilteredByDate(String ssn, LocalDate filterDate);

}
