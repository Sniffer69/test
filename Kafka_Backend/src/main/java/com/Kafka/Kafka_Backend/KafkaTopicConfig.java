package com.Kafka.Kafka_Backend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig {
    @Bean
    public NewTopic kafkaTopic(){
        return TopicBuilder.name("Kannel")
                .partitions(10)
                .build();
    }
}
