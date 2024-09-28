package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient>  findBySsn(String ssn);
}
