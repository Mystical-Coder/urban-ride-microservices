package com.urbanride.websocket.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideResponseDto {

    public Boolean response;

    public Long bookingId;

}
