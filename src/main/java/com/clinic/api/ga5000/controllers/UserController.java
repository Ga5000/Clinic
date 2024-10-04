package com.clinic.api.ga5000.controllers;

import com.clinic.api.ga5000.entities.enums.USState;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get list of US states",
            description = "Retrieves a list of all US states as defined in the USState enum.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of US states retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = USState.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<USState>> getStates() {
        List<USState> states = Arrays.stream(USState.class.getEnumConstants()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(states);
    }

}
