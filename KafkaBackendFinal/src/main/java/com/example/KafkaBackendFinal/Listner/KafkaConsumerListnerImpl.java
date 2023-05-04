package com.example.KafkaBackendFinal.Listner;
import com.example.KafkaBackendFinal.Producer.KafkaProducerServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerListnerImpl implements KafkaListnerService{
    @Autowired
    private KafkaAdmin kafkaAdmin;
    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    @KafkaListener(id = "KafkaGroup", topicPattern = ".*")
    public void listenAllTopics(ConsumerRecord<String, String> record) {
        LOGGER.info("Consumed message is: {}",record);
    }
}