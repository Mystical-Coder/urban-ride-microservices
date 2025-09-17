package com.urbanride.websocket.dto;


import lombok.*;

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

    private List<Long> driverId;

    private Long bookingId;


}
