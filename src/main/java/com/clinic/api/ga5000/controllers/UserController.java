package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.entities.enums.USState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/states")
    public ResponseEntity<List<USState>> getStates() {
        List<USState> states = Arrays.stream(USState.class.getEnumConstants()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(states);
    }
}
