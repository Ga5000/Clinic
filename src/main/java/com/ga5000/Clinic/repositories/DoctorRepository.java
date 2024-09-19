package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Doctor;
import com.ga5000.Clinic.entities.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,String> {

    Optional<Doctor> findByName(String name);

    List<Doctor> findBySpeciality(Speciality speciality);

    @Query("SELECT d FROM Doctor d JOIN d.insurances i WHERE i.insuranceId = :insuranceId")
    List<Doctor> findDoctorsByInsurance(@Param("insuranceId") Long insuranceId);
}
