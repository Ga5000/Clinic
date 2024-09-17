package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.AppointmentStatus;
import com.ga5000.Clinic.entities.enums.Speciality;
import com.ga5000.Clinic.utils.FeeUtil;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private double fee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment(Long appointmentId, LocalDate date, LocalTime time, Doctor doctor, Patient patient,
                       double fee, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.patient = patient;
        this.status = AppointmentStatus.SCHEDULED;
        setFeeBasedOnSpeciality(doctor.getSpeciality());
        applyDiscountIfApplicable();
    }

    public Appointment(){}

    private void setFeeBasedOnSpeciality(Speciality speciality){
        switch (speciality) {
            case CARDIOLOGY, ORTHOPEDICS:
                this.fee = 200.0;
                break;
            case DERMATOLOGY:
                this.fee = 180.0;
                break;
            case ENDOCRINOLOGY:
                this.fee = 190.0;
                break;
            case GASTROENTEROLOGY, UROLOGY:
                this.fee = 185.0;
                break;
            case NEUROLOGY:
                this.fee = 210.0;
                break;
            case PEDIATRICS:
                this.fee = 170.0;
                break;
            case PSYCHIATRY:
                this.fee = 175.0;
                break;
            case RADIOLOGY:
                this.fee = 195.0;
                break;
            default:
                throw new IllegalArgumentException("Unknown speciality: " + speciality);
        }
    }

    private void applyDiscountIfApplicable(){
        List<Insurance> patientInsurances = patient.getInsurances();
        List<Insurance> doctorInsurances = doctor.getInsurances();

        double highestCoPaymentPercentage = getHighestCoPaymentPercentage(patientInsurances, doctorInsurances);

        if (highestCoPaymentPercentage > 0) {
            this.fee = FeeUtil.applyCoPaymentPercentage(this.fee, highestCoPaymentPercentage);
        }
    }

    private static double getHighestCoPaymentPercentage(List<Insurance> patientInsurances,
                                                        List<Insurance> doctorInsurances) {
        double highestCoPaymentPercentage = 0.0;

        for (Insurance patientInsurance : patientInsurances) {
            for (Insurance doctorInsurance : doctorInsurances) {
                if (patientInsurance.equals(doctorInsurance)) {
                    double coPaymentPercentage = doctorInsurance.getCoPaymentPercentage();
                    if (coPaymentPercentage > highestCoPaymentPercentage) {
                        highestCoPaymentPercentage = coPaymentPercentage;
                    }
                }
            }
        }
        return highestCoPaymentPercentage;
    }


    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        setFeeBasedOnSpeciality(doctor.getSpeciality());
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
