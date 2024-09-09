package com.ga5000.Clinic.dtos.Staff;

import com.ga5000.Clinic.entities.enums.Gender;
import com.ga5000.Clinic.entities.enums.Role;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.utils.ShiftMapping;

import java.time.LocalDate;

public record StaffDTO(Long staffId, String name, String email, int age, LocalDate birthDate,
                       Speciality speciality, Gender gender, Role role, ShiftMapping.TimeRange shift) {
}
