package com.example.Kafka_Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/kafka/api")
public class KafkaController {
    @Autowired
    Producer producer;

    @PostMapping(value = "/producer")
    public String sendmsg (@RequestParam("message") String message)  {
        producer.sendMsgToTopic(message);
        return "\nMessage is Successfully Sent";

    }

}
