package com.urbanride.booking.dto;


import lombok.*;
import org.example.uberprojectentityservice.models.ExactLocation;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RideRequestDto {

    private Long passengerId;

//    private ExactLocation startLocation;
//
//    private ExactLocation endLocation;

    private List<Long> driverIds;

    private Long bookingId;


}
