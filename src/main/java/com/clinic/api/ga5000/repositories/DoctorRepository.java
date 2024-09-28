package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByMedicalLicense(String medicalLicense);
}
