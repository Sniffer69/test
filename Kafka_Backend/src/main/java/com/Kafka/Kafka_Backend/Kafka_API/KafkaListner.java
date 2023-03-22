package com.Kafka.Kafka_Backend.Kafka_API;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {
    @KafkaListener(topics = "KafkaTopic", groupId = "001")
    public void listenmsg(String messageReceived){
        System.out.println("Message is :"+ messageReceived);
    }
}
