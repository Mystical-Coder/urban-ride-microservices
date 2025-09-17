package com.urbanride.auth.services;

import com.urbanride.auth.dtos.PassengerDto;
import com.urbanride.auth.dtos.PassengerSignupRequestDto;
import com.urbanride.auth.repositories.PassengerRepository;
import org.example.uberprojectentityservice.models.Passenger;
import org.springframework.security.crypto.password.PasswordEncoder; // Import the interface
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // Depend on the interface, not the concrete class
    private final PasswordEncoder passwordEncoder;

    private final PassengerRepository passengerRepository;

    // Update the constructor to accept the interface
    public AuthService(PassengerRepository passengerRepository, PasswordEncoder passwordEncoder) {
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public PassengerDto singupPassenger(PassengerSignupRequestDto passengerSignupRequestDto){
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                // Use the interface method, no change needed here
                .password(passwordEncoder.encode(passengerSignupRequestDto.getPassword()))
                .name(passengerSignupRequestDto.getName())
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        return PassengerDto.fromModel(newPassenger);
    }
}