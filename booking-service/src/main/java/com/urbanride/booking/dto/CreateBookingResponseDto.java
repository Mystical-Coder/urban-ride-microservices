package com.urbanride.booking.dto;


import lombok.*;
import org.example.uberprojectentityservice.models.Driver;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateBookingResponseDto {

    private long bookingId;

    private String bookingStatus;

    private Optional<Driver> driver;

}
