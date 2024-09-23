package com.ga5000.Clinic.services;

import com.ga5000.Clinic.dtos.AppointmentDTO;
import com.ga5000.Clinic.repositories.AppointmentRepository;
import com.ga5000.Clinic.services.interfaces.AppointmentService;
import com.ga5000.Clinic.utils.DtoConverter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;

    }

    @Override
    @Transactional
    public void setAsFinished(UUID appointmentId) {
        appointmentRepository.setAsFinished(appointmentId);
    }

    @Override
    public List<AppointmentDTO> getAll() {
        return appointmentRepository.findAll()
                .stream().map(DtoConverter::convertToAppointmentDTOUsingAppointment).toList();
    }
}
