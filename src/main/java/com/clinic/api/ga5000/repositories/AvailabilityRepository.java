package com.clinic.api.ga5000.repositories;

import com.clinic.api.ga5000.entities.DoctorAvailability;
import com.clinic.api.ga5000.entities.enums.Speciality;
import com.clinic.api.ga5000.entities.enums.USState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
@Repository
public interface AvailabilityRepository extends JpaRepository<DoctorAvailability, UUID> {
    List<DoctorAvailability> findByDoctorMedicalLicense(String medicalLicense);

    @Query("SELECT da.doctor FROM DoctorAvailability da " +
            "LEFT JOIN da.doctor.appointments a " +
            "WHERE da.date = :date " +
            "AND da.startTime <= :time " +
            "AND da.endTime >= :endTime " +
            "AND da.doctor.address.city = :city " +
            "AND da.doctor.address.state = :state " +
            "AND (a IS NULL OR (a.appointmentTime NOT BETWEEN :startTime AND :endTime)) " +
            "AND da.doctor.speciality = :speciality")
    List<DoctorAvailability> findAvailableDoctorsByDateTimeAndLocationAndSpeciality(@Param("date") LocalDate date,
                                                                                    @Param("time") LocalTime time,
                                                                                    @Param("endTime") LocalTime endTime,
                                                                                    @Param("state") USState state,
                                                                                    @Param("city") String city,
                                                                                    @Param("speciality") Speciality speciality);
}
