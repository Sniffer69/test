package com.Kafka.Kafka_Backend.Kafka_Json;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/kafka/json")
public class KafkaJsonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonController.class);
    @Autowired
    private KafkaJsonProducer kafkaJsonProducer;

    @Autowired
    private KafkaAdmin kafkaAdmin;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping("/topics")
    public ResponseEntity<String> createTopic(@RequestParam("topicName") String topicName) throws ExecutionException, InterruptedException {
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "your-group-id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        AdminClient adminClient = AdminClient.create(props);
        adminClient.createTopics(Arrays.asList(newTopic)).all().get();;
        LOGGER.info(String.format("created topic is: %s",newTopic.name()));
        return ResponseEntity.ok("Topic created successfully");
    }




    @PostMapping("/jsonpublish")
    public ResponseEntity<String> publishjson (@RequestBody User user){
        kafkaJsonProducer.sendjsonmsg(user);
        return ResponseEntity.ok("Message has been successfully send to the topic");
    }

}
