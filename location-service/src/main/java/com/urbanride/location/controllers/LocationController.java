package com.urbanride.location.controllers;

import com.urbanride.location.dto.DriverLocationDto;
import com.urbanride.location.dto.NearbyDriversRequestDto;
import com.urbanride.location.dto.SaveDriverLocationRequestDto;
import com.urbanride.location.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/drivers")
    public ResponseEntity<?> saveDriverLocation(@RequestBody SaveDriverLocationRequestDto saveDriverLocationRequestDto) {
        try {
            Boolean response = locationService.saveDriverLocation(saveDriverLocationRequestDto.getDriverId(), saveDriverLocationRequestDto.getLatitude(), saveDriverLocationRequestDto.getLongitude());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/nearby/drivers")
    public ResponseEntity<List<DriverLocationDto>> getNearbyDrivers(@RequestBody NearbyDriversRequestDto nearbyDriversRequestDto) {
        try {
            List<DriverLocationDto> list = locationService.getNearByDrivers(nearbyDriversRequestDto.getLatitude(), nearbyDriversRequestDto.getLongitude());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
