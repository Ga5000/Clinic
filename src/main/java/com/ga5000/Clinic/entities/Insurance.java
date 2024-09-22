package com.ga5000.Clinic.entities;

import com.ga5000.Clinic.entities.enums.InsuranceType;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Insurance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;

    @Column(nullable = false)
    private String enterprise;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsuranceType insuranceType;

    @Column(nullable = false)
    private LocalDate expiresAt;

    @Column(nullable = false)
    private double coPaymentPercentage;

    public Insurance(String enterprise, InsuranceType insuranceType, LocalDate expiresAt,
                     double coPaymentPercentage) {
        this.enterprise = enterprise;
        this.insuranceType = insuranceType;
        this.expiresAt = expiresAt;
        setDefaultCoPaymentPercentage(insuranceType);

    }

    public Insurance(){}

    private void setDefaultCoPaymentPercentage(InsuranceType type) {
        switch (type) {
            case BASIC, INDIVIDUAL:
                this.coPaymentPercentage = 20.0;
                break;
            case STANDARD:
                this.coPaymentPercentage = 15.0;
                break;
            case PREMIUM:
                this.coPaymentPercentage = 10.0;
                break;
            case FAMILY:
                this.coPaymentPercentage = 25.0;
                break;
            case CORPORATE:
                this.coPaymentPercentage = 12.0;
                break;
            case GOVERNMENT:
                this.coPaymentPercentage = 5.0;
                break;
            case STUDENT:
                this.coPaymentPercentage = 18.0;
                break;
            case SENIOR:
                this.coPaymentPercentage = 8.0;
                break;
            case DENTAL:
                this.coPaymentPercentage = 30.0;
                break;
            default:
                throw new IllegalArgumentException("Unknown insurance type: " + type);
        }
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDate expiresAt) {
        this.expiresAt = expiresAt;
    }

    public double getCoPaymentPercentage() {
        return coPaymentPercentage;
    }

    public void setCoPaymentPercentage(double coPaymentPercentage) {
        this.coPaymentPercentage = coPaymentPercentage;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Insurance insurance = (Insurance) object;
        return Objects.equals(insuranceId, insurance.insuranceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(insuranceId);
    }
}
