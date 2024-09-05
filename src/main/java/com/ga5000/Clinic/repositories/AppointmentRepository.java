package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
