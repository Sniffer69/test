package com.Kafka.Kafka_Backend.Kafka_Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaJsonListner {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaJsonListner.class);
    private List<String> user = new ArrayList<>();

//    @KafkaListener(topics = "TestTopic", groupId = "myGroup")
//    public List<String> listenjsonmsg(User user){
//        LOGGER.info(String.format("Json Consumed msg: %s",user.toString()));
//        return (List<String>) ResponseEntity.ok("200 ok");
//
//    }

    @KafkaListener(topics = "kannel", groupId = "myGroup")
    public HttpStatus listenjsonmsg(User user){
        LOGGER.info(String.format("Json Consumed msg: %s",user.toString()));
        return HttpStatus.ACCEPTED;
    }

//    @KafkaListener(topics = "TestTopic", groupId = "myGroup")
//    public void onMessage (ConsumerRecord<String, String> record) {
//        String messageId = record.key();
//        LOGGER.info(String.format("Json Consumed id: %s",messageId));
//        String message = record.value();
//        LOGGER.info(String.format("Json Consumed msg: %s",message));
//
//    }


}
