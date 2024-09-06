package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import com.ga5000.Clinic.entities.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select a.appointmentDate, a.appointmentTime, a.speciality, s.name " +
            "from Appointment a join a.doctor s " +
            "where a.patient.patientId = :patientId and a.status = 'SCHEDULED' " +
            "order by a.appointmentDate ASC, a.appointmentTime ASC ")

    List<Appointment> findScheduledAppointments(@Param("patientId") Long patientId);

    @Query("select a.appointmentDate, a.appointmentTime, a.speciality, s.name " +
            "FROM Appointment a " +
            "JOIN a.doctor s " +
            "where a.patient.patientId = :patientId and a.status = 'COMPLETED' " +
            "order by a.appointmentDate ASC, a.appointmentTime ASC")

    List<Appointment> findAppointmentHistory(@Param("patientId") Long patientId);

    @Query("select a.appointmentDate, a.appointmentTime, a.speciality, s.name " +
            "FROM Appointment a " +
            "JOIN a.doctor s " +
            "where a.patient.patientId = :patientId and a.status = 'CANCELED' " +
            "order by a.appointmentDate ASC, a.appointmentTime ASC")
    List<Appointment> findCanceledAppointments(@Param("patientId") Long patientId);

    @Query("select a.appointmentDate, a.appointmentTime, a.speciality, s.name " +
            "FROM Appointment a " +
            "JOIN a.doctor s " +
            "where a.patient.patientId = :patientId " +
            "order by a.appointmentDate ASC, a.appointmentTime ASC")
    List<Appointment> findAllAppointments(@Param("patientId") Long patientId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.patient.patientId = :patientId AND a.id = :appointmentId")
    void deleteAppointmentByPatientIdAndAppointmentId(@Param("patientId") Long patientId,
                                                      @Param("appointmentId") Long appointmentId);

}
