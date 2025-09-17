package com.urbanride.auth.dtos;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {

    private String email;

    private String password;

    private String phoneNumber;

    private String name;

}
