package com.Kafka.Kafka_Backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListener.class);

    @KafkaListener(topics = "Kannel",groupId = "myGroup")
    public void listenmsg(String recievedmsg){
        LOGGER.info(String.format("Consumed msg is: %s ",recievedmsg));
    }
}
