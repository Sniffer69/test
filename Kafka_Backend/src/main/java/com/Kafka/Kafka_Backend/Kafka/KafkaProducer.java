package com.Kafka.Kafka_Backend.Kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    public void sendmsg (String message){
        LOGGER.info(String.format("Producer Message is : %s",message));
        kafkaTemplate.send("Kannel",message);

    }

}
