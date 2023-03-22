package com.Kafka.Kafka_Backend.Kafka_Data;

import com.Kafka.Kafka_Backend.Dynamic_Topic.TopicDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaRepo extends JpaRepository <KafkaUserDao, String> {

}
