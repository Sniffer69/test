package com.Kafka.Kafka_Backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonListner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    @KafkaListener(topics = "KannelJson",groupId = "myGroup")
    public void listenjsonmsg(User user){
        LOGGER.info(String.format("Json Consumed msg: %s",user.toString()));
    }
}
