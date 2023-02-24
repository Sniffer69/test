package com.example.Kafka_Implementation;

import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerService {

    @KafkaListener(topics = "code_Decode_Topic", groupId = "codedecode-group")
    public void listenToCodeDecodeKafkaTopic(String messageReceived) {
        System.out.println("Message received is " + messageReceived);
    }
}
