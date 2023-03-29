package com.Kafka.Kafka_Backend.Kafka_Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonListner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonListner.class);
    private KafkaJsonRepo kafkaJsonRepo;



    @KafkaListener(topics ="your-topic", groupId = "myGroup")
    public void listenjsonmsg(User user){
        LOGGER.info(String.format("Json Consumed msg: %s",user.toString()));
    }
}
