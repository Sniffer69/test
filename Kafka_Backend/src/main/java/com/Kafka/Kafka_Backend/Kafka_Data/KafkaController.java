package com.Kafka.Kafka_Backend.Kafka_Data;

import com.Kafka.Kafka_Backend.Kafka_API.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka/api")
public class KafkaController {
    @Autowired
    KafkaProducer producer;
    @PostMapping(value = "/producer")
    public String sendmsg (@RequestParam("message") String message) {
        producer.sendMsgToTopic(message);
        return "\nMessage is Successfully Sent";
    }
}
