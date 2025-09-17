package com.urbanride.booking.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearbyDriversRequestDto {

    Double latitude;

    Double longitude;

}
