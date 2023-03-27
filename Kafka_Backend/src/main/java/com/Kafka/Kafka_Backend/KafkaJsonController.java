package com.Kafka.Kafka_Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka/json")
public class KafkaJsonController {
    @Autowired
    private KafkaJsonProducer kafkaJsonProducer;

    @PostMapping("/jsonpublish")
    public ResponseEntity<String> publishjson (@RequestBody User user){
        kafkaJsonProducer.sendjsonmsg(user);
        return ResponseEntity.ok("Message has been successfully send to the topic");
    }
}
