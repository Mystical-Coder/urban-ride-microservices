package com.urbanride.booking.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.BookingStatus;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingRequestDto {

    private String status;

    private Optional<Long> driverId;

}
