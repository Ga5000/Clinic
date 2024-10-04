package com.clinic.api.ga5000.config;

import com.clinic.api.ga5000.security.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/auth/account/delete").hasAnyRole("ADMIN","PATIENT", "DOCTOR")
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/patients/all-patients").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/patients/patient-info/**").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers(HttpMethod.GET,"/api/doctors/doctor-info/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/doctors/all-doctors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/doctors/specialities").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/users/states").permitAll()
                        .requestMatchers(HttpMethod.POST,"api/doctor/availability/add").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/availability/doctor/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.DELETE,"/api/doctor/availability/delete/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.PUT,"/api/availability/update/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/doctor/availability/find/**").hasAnyRole("ADMIN", "DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/availability/find").hasAnyRole("ADMIN", "DOCTOR","PATIENT")
                        .requestMatchers(HttpMethod.POST,"/api/appointments/make").hasAnyRole("ADMIN", "PATIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/appointments/cancel").hasAnyRole("ADMIN", "PATIENT", "DOCTOR")
                        .requestMatchers(HttpMethod.PUT,"/api/appointments/cancel/all").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers(HttpMethod.PUT,"/api/appointments/mark-as-finished").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/appointments/doctor/**").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/appointments/patient/**").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers(HttpMethod.GET,"/api/appointments/find/doctor-date-range").hasAnyRole("ADMIN","DOCTOR")
                        .requestMatchers(HttpMethod.GET,"/api/appointments/find/ssn-date-range").hasAnyRole("ADMIN","PATIENT")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
