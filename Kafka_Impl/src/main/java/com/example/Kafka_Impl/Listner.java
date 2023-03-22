package com.example.Kafka_Impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listner {
    @KafkaListener(topics = "kafkaTopic", groupId = "KafkaID")
    public void listenmsg(String messageReceived){
        System.out.println("Message is :" + messageReceived);
    }
}
