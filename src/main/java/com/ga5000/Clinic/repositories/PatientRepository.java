package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select a.appointmentDate, a.appointmentTime, a.speciality, s.name " +
            "from Appointment a join a.doctor s " +
            "where a.patient.patientId = :patientId and a.status = 'SCHEDULED' " +
            "order by a.appointmentDate ASC, a.appointmentTime ASC ")

    List<Appointment> findScheduledAppointments(@Param("patientId") Long patientId);
}
