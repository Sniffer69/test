package com.Kafka.Kafka_Backend.Kafka_Json;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.hibernate.query.spi.QueryPlanCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonProducer.class);
    @Autowired
    KafkaTemplate<String, User>  kafkaTemplate;
    private KafkaAdmin kafkaAdmin;

    private User user;
    @Autowired
    private KafkaJsonRepo kafkaJsonRepo;

    public void sendjsonmsg(User data){
        String topicName = "myTopic";
//        LOGGER.info(String.format("Json Inserted Data Message : %s", data.toString()));
//        kafkaJsonRepo.save(data);
        Message<User> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "Test")
                .build();


        String topic = "KannelTest";
        List<PartitionInfo> partitions = kafkaTemplate.partitionsFor(topic);
        List<Integer> partitionNumbers = new ArrayList<>();
        for (PartitionInfo partition : partitions) {
            int key = data.getId();
            ProducerRecord p = new ProducerRecord<>("KannelTest",partition.partition(),null ,data);
            kafkaTemplate.send(p);
        }


//        ProducerRecord<Integer, User> record = new ProducerRecord<>("Test", key, data);
//        LOGGER.info(String.format("Json Inserted Data key : %s", key));
//        LOGGER.info(String.format("Json Inserted Data value : %s", data));
//            kafkaTemplate.send("Test", "Key", data);


    }
}
