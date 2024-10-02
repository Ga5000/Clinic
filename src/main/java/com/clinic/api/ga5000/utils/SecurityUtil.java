package com.clinic.api.ga5000.utils;

import com.clinic.api.ga5000.entities.UserEntity;
import com.clinic.api.ga5000.entities.enums.Role;
import com.clinic.api.ga5000.exceptions.UserNotFoundException;
import com.clinic.api.ga5000.repositories.UserEntityRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    private final UserEntityRepository userEntityRepository;

    public SecurityUtil(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();
            }
        }

        return null;
    }

    public boolean isAuthorized(String email) {
        String currentUsername = getCurrentUsername();
        UserEntity user = userEntityRepository.findByEmail(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return currentUsername.equals(email) || user.getRole() == Role.ADMIN;
    }



}
