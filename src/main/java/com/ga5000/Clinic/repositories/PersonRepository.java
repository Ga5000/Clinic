package com.ga5000.Clinic.repositories;

import com.ga5000.Clinic.entities.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByEmail(String email);
}
