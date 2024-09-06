package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.entities.enums.AgeGroup;
import org.springframework.stereotype.Component;

@Component
public class SetAgeGroup {
    public static AgeGroup setGroup(int age) {
        return age < 18 ? AgeGroup.CHILD
                : age < 60 ? AgeGroup.ADULT
                : AgeGroup.ELDERLY;
    }
}
