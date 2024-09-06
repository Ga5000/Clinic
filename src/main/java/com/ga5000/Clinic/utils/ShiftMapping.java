package com.ga5000.Clinic.utils;

import com.ga5000.Clinic.entities.enums.Shift;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ShiftMapping {
    private static final Map<Shift, TimeRange> shiftToTimeRange = new HashMap<>();

    static {
        shiftToTimeRange.put(Shift.MORNING, new TimeRange(LocalTime.of(6, 0), LocalTime.of(14, 0)));
        shiftToTimeRange.put(Shift.AFTERNOON, new TimeRange(LocalTime.of(14, 0), LocalTime.of(22, 0)));
    }

    @Override
    public String toString() {
        return shiftToTimeRange.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    public static TimeRange getTimeRange(Shift shift) {
        return shiftToTimeRange.get(shift);
    }

    public record TimeRange(LocalTime startTime, LocalTime endTime) {
    }
}
