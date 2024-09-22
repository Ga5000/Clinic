package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Insurance;
import com.ga5000.Clinic.entities.enums.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Long> {

   List<Insurance> findByEnterprise(String enterprise);





}
