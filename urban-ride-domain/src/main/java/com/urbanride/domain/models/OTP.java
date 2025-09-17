package com.urbanride.domain.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Builder
public class OTP extends BaseModel{

    private String code;

    private String sentToNumber;

    public static OTP make(String phoneNumber){
        Random random = new Random();

        Integer code = random.nextInt(9000) + 1000;

        return OTP.builder().sentToNumber(phoneNumber).code(code.toString()).build();
    }
}
