package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Staff;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    @Query("SELECT p.name, p.age, p.birthDate, p.gender, p.ageGroup, a.appointmentTime " +
            "FROM Appointment a " +
            "JOIN a.patient p " +
            "JOIN a.doctor s " +
            "WHERE s.staffId = :doctorId " +
            "AND a.appointmentDate = CURRENT_DATE " +
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

}
