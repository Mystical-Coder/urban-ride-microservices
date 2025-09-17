package com.urbanride.websocket.controller;


import com.urbanride.websocket.dto.TestRequest;
import com.urbanride.websocket.dto.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    TestResponse pingCheck(TestRequest message){
        System.out.println("Received message from client " + message.getData());
        return TestResponse.builder().data("Received").build();
    }

//    @Scheduled(fixedDelay = 2000)
    public void sendPeriodicMessage(){
        System.out.println("Executed periodic function");
        simpMessagingTemplate.convertAndSend("/topic/scheduled", "Periodic message sent " + System.currentTimeMillis());
    }

}
