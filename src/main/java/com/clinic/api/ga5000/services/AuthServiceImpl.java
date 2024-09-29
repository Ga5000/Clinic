package com.clinic.api.ga5000.services;

import com.clinic.api.ga5000.repositories.UserEntityRepository;
import com.clinic.api.ga5000.services.interfaces.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthServiceImpl implements AuthService {
    private final UserEntityRepository userRepository;

    public AuthServiceImpl(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
