package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
    Set<Insurance> findInsurancesByPatientSsn(String ssn);
}
