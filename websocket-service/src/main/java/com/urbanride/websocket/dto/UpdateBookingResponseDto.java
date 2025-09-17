package com.urbanride.websocket.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.BookingStatus;
import org.example.uberprojectentityservice.models.Driver;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingResponseDto {

    private Long bookingId;
    private BookingStatus status;

    private Optional<Driver> driver;

}
