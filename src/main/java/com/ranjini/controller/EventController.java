package com.ranjini.controller;

import com.ranjini.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")

public class EventController {
    @Autowired
   private KafkaMessagePublisher publisher;
@GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
    try {
        for (int i = 0; i < 10; i++) {
            publisher.sendMessageToTopic(message + ":" + i);
        }
        return ResponseEntity.ok("Message published Successfully:");
    }
    catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}
