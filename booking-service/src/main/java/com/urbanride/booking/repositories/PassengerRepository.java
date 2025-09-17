package com.urbanride.booking.repositories;

import org.example.uberprojectentityservice.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
