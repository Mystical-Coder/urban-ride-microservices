package com.urbanride.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car extends BaseModel{

    @Column(unique = true, nullable = false)
    private String plateNumber;

    private String brand;

    @ManyToOne
    private Color color;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @OneToOne
    private Driver driver;
}
