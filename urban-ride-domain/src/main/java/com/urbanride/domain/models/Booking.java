package com.urbanride.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/** Dont need to add indexing, spring boot will automatically added for us
 @Table(indexes = {
 @Index(columnList = "driver_id")
 })
 */

public class Booking extends BaseModel{

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Passenger passenger;

    @OneToOne(cascade = CascadeType.PERSIST)
    private ExactLocation startLocation;


    @OneToOne(cascade = CascadeType.PERSIST)
    private ExactLocation endLocation;
}
