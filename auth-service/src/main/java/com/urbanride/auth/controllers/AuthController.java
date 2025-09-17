package com.urbanride.auth.controllers;

import com.urbanride.auth.dtos.AuthRequestDto;
import com.urbanride.auth.dtos.AuthResponseDto;
import com.urbanride.auth.dtos.PassengerDto;
import com.urbanride.auth.dtos.PassengerSignupRequestDto;
import com.urbanride.auth.services.AuthService;
import com.urbanride.auth.services.JwtService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// Updated the base request mapping
@RestController
@EnableDiscoveryClient
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // Updated the signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto) {
        PassengerDto passengerDto = authService.singupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(passengerDto, HttpStatus.CREATED);
    }

    // Updated the login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> signIn(@RequestBody AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String jwtToken = jwtService.createToken(authRequestDto.getEmail());
            AuthResponseDto response = new AuthResponseDto(jwtToken);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // Return a proper unauthorized status if authentication fails
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}