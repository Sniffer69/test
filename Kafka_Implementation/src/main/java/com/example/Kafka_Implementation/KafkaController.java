package com.example.Kafka_Implementation;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/kafka/api")
public class KafkaController {
   @Autowired
   ProducerService producerService;
   @GetMapping("/getmessage")
    public String getMessageFromClient(@RequestParam("This is my message") String message){
       producerService.sendMsgToTopic(message);
       return "Message sent Successfully to the your code decode topic ";

    }
}
