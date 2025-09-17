package com.urbanride.auth.dtos;

import lombok.*;
import org.example.uberprojectentityservice.models.Passenger;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Date createdAt;

    public static PassengerDto fromModel(Passenger passenger){
        PassengerDto result = PassengerDto.builder()
                .id(passenger.getId().toString())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhoneNumber())
                .createdAt(passenger.getCreatedAt())
                .build();
        return result;
    }


}
