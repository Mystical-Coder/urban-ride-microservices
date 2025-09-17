package com.urbanride.review.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Passenger extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings;

}
