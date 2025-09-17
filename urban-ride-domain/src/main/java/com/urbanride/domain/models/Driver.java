package com.urbanride.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseModel{

    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    private String aadharCard;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private Car car;

    @Enumerated(value = EnumType.STRING)
    private DriverApprovalStatus driverApprovalStatus;

    @OneToOne
    private ExactLocation lastknownLocation;

    @OneToOne
    private ExactLocation home;

    private String activeCity;

    private String phoneNumber;

    @DecimalMin(value = "0.00", message = "Rating must be greater than or equal to 0.00")
    @DecimalMax(value = "5.00", message = "Rating must be less than or equal to 5.00")
    private Double rating;

    @OneToMany(mappedBy = "driver")
    @Fetch(FetchMode.SUBSELECT) // for removing n + 1 problem
    @JsonIgnore // for removing nested references
    private List<Booking> bookings;

    private boolean isAvailable;

}
