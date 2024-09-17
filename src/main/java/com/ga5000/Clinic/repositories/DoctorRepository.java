package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,String> {
}
