package com.urbanride.domain.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Builder
public class ExactLocation extends BaseModel{

    private Double latitude;

    private Double longitude;

}
