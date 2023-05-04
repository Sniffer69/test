package com.example.KafkaBackendFinal.Listner;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KafkaListnerService {
    public void listenAllTopics(ConsumerRecord<String, String> record);

}




