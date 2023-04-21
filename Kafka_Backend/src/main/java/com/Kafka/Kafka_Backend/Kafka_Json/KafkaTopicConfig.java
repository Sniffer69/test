package com.Kafka.Kafka_Backend.Kafka_Json;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



@Configuration
@EnableKafka
public class KafkaTopicConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Autowired
    private KafkaAdmin kafkaAdmin;

//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
//        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }






    @Bean
    public KafkaAdminClient kafkaAdminClient() {
        Properties configProps = new Properties();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");

//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        configProps.putAll(kafkaAdmin.getConfigurationProperties());
        return (KafkaAdminClient) AdminClient.create(configProps);
    }

//    @Bean
//    public KafkaConsumer<String, String> kafkaConsumer() {
//        Map<String, Object> configs = new HashMap<>();
//
//        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
//        return new KafkaConsumer<>(configs);
//    }



//    @Bean
//    public NewTopic kafkaJsonTopic() {
//        return TopicBuilder.name("test")
//                .partitions(2)
//                .replicas(1)
//                .build();
//    }



    }






