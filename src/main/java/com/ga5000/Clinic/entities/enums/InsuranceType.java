package com.ga5000.Clinic.entities.enums;

public enum InsuranceType {

    BASIC("Básico"),
    STANDARD("Padrão"),
    PREMIUM("Premium"),
    FAMILY("Família"),
    CORPORATE("Corporativo"),
    GOVERNMENT("Governo"),
    STUDENT("Estudante"),
    SENIOR("Sênior"),
    INDIVIDUAL("Individual"),
    DENTAL("Odontológico");

    private final String portugueseName;


    InsuranceType(String portugueseName) {
        this.portugueseName = portugueseName;
    }


    public String getPortugueseName() {
        return portugueseName;
    }
}
