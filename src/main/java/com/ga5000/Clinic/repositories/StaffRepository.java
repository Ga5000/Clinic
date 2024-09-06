package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Staff;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
