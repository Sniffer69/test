package com.Kafka.Kafka_Backend.Kafka_Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducer.class);
    @Autowired
    KafkaTemplate<String, User>  kafkaTemplate;
    private KafkaAdmin kafkaAdmin;
    @Autowired
    private KafkaJsonRepo kafkaJsonRepo;
    public void sendjsonmsg(User data){
        LOGGER.info(String.format("Json Inserted Data Message : %s", data.toString()));
        kafkaJsonRepo.save(data);
        Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "1")
                .build();
        kafkaTemplate.send(message);
    }
}
