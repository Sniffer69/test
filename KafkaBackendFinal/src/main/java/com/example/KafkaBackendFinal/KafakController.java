package com.example.KafkaBackendFinal;

import com.example.KafkaBackendFinal.Producer.KafkaProducerService;
import com.example.KafkaBackendFinal.Producer.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/kafka/")
public class KafakController {

    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private ConsumerFactory<String, String> consumerFactory;
    @Autowired
    private KafkaListenerEndpointRegistry registry;
    @Autowired
    private KafkaAdmin kafkaAdmin;
    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;


    @PostMapping("/sendmessage/{topic}")
    public ResponseEntity<String> sendjsonmsg(@PathVariable String topic, @RequestBody UserDao userDao){
        kafkaProducerService.sendMessage(topic, userDao);
        return ResponseEntity.ok("Message has been successfully send to the topic");
    }




}
