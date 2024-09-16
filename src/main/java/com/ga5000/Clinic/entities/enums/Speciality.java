package com.ga5000.Clinic.entities.enums;

public enum Speciality {
    CARDIOLOGY("Cardiologia"),
    DERMATOLOGY("Dermatologia"),
    ENDOCRINOLOGY("Endocrinologia"),
    GASTROENTEROLOGY("Gastroenterologia"),
    NEUROLOGY("Neurologia"),
    ORTHOPEDICS("Ortopedia"),
    PEDIATRICS("Pediatria"),
    PSYCHIATRY("Psiquiatria"),
    RADIOLOGY("Radiologia"),
    UROLOGY("Urologia");

    private final String description;

    Speciality(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
