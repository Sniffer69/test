package com.Kafka.Kafka_Backend.Kafka_API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate <String, String> kafkaTemplate;
    public void sendMsgToTopic (String message){

        kafkaTemplate.send("KafkaTopic",message);
    }

}
