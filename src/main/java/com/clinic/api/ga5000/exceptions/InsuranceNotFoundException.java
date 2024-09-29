package com.clinic.api.ga5000.exceptions;

public class InsuranceNotFoundException extends RuntimeException {
  public InsuranceNotFoundException(String message) {
    super(message);
  }
}
