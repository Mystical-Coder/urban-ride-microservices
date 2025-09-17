package com.urbanride.booking.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverLocationDto {

    String driverId;

    Double latitude;

    Double longitude;
}
