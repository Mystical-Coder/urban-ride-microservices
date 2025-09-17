package com.urbanride.websocket.controller;

import com.urbanride.websocket.dto.RideRequestDto;
import com.urbanride.websocket.dto.RideResponseDto;
import com.urbanride.websocket.dto.UpdateBookingRequestDto;
import com.urbanride.websocket.dto.UpdateBookingResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("api/socket")
public class DriverRequestController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final RestTemplate restTemplate;



    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/newride")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto requestDto) {
        sendDriverNewRideRequest(requestDto);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void sendDriverNewRideRequest(RideRequestDto requestDto) {
        System.out.println("Executed periodic function");
//        TODO: Ideally the request should only go to nearby drivers, but for simplicity we send it everyone
        simpMessagingTemplate.convertAndSend("/topic/rideRequest", requestDto);
    }

//    We can use locking
    @MessageMapping("/rideResponse/{userId}")
    public void rideResponseHanlder(@DestinationVariable String userId, RideResponseDto rideResponseDto){
        System.out.println(rideResponseDto.getResponse()+" "+userId);
        UpdateBookingRequestDto requestDto = UpdateBookingRequestDto.builder()
                .driverId(Optional.of(Long.parseLong(userId)))
                .status("SCHEDULED")
                .build();
        ResponseEntity<UpdateBookingResponseDto> result = this.restTemplate.postForEntity("http://localhost:7478/api/v1/booking/" + rideResponseDto.bookingId, requestDto, UpdateBookingResponseDto.class);
    }

}
