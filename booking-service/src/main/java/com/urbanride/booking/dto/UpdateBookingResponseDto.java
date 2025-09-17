package com.urbanride.booking.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.BookingStatus;
import org.example.uberprojectentityservice.models.Driver;

import javax.swing.text.html.Option;
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
