package com.example.Kafka_Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMsgToTopic(String message){
        kafkaTemplate.send("code_Decode_Topic", message);
    }
}
