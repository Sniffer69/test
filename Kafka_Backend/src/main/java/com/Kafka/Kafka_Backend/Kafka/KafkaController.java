package com.Kafka.Kafka_Backend.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;
    @GetMapping("/publish")
       public ResponseEntity <String> publish (@RequestParam("message")String message){
       kafkaProducer.sendmsg(message);
       return ResponseEntity.ok("Message has been successfully send to the topic");
   }

}
