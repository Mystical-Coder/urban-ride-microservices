package com.urbanride.booking.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.ExactLocation;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateBookingDto {

    private Long passengerId;

    private ExactLocation startLocation;

    private ExactLocation endLocation;

}
