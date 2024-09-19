package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

    Optional<Insurance> findByEnterprise(String enterprise);
}
