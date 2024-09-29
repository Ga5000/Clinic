package com.clinic.api.ga5000.services.interfaces;

import com.clinic.api.ga5000.entities.UserEntity;

public interface TokenService {
    String generateToken(UserEntity user);
    String validateToken(String token);

}
