package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Notification;
import com.ga5000.Clinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("SELECT p FROM Patient p JOIN Appointment a WHERE a.date = :tomorrow ")
    List<Patient> findPatientsWithAppointmentsOneDayPrior(@Param("tomorrow") LocalDate tomorrow);
}
