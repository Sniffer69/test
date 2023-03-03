package com.example.Kafka_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/api")
public class KafkaController {
    @Autowired
    Producer producer;

    @GetMapping(value = "/producer")
    public String sendmsg(@RequestParam("message") String message){
        producer.sendMsgToTopic(message);
        return "Message is Successfully Sent";

    }

}
