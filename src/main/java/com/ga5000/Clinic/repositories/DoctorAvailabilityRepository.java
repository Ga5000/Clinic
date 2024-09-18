package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.DoctorAvailability;
import com.ga5000.Clinic.entities.enums.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    @Query("SELECT da FROM DoctorAvailability da WHERE da.doctor.speciality = :speciality AND da.date = :date")
    List<DoctorAvailability> findBySpecialityAndDate(Speciality speciality, LocalDate date);
}
