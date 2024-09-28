package com.clinic.api.ga5000.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Insurance {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID insuranceId;

    @Column(nullable = false)
    private String providerName;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private String coverageDetails;

    @Column(nullable = false)
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "ssn", referencedColumnName = "ssn", nullable = false)
    private Patient patient;

    public Insurance() {}

    public Insurance(String providerName, String policyNumber, String coverageDetails, String contactNumber, Patient patient) {
        this.providerName = providerName;
        this.policyNumber = policyNumber;
        this.coverageDetails = coverageDetails;
        this.contactNumber = contactNumber;
        this.patient = patient;
    }

    public UUID getInsuranceId() {
        return insuranceId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
