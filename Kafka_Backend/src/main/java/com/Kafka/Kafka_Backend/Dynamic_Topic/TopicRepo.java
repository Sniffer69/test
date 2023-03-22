package com.Kafka.Kafka_Backend.Dynamic_Topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TopicRepo extends JpaRepository <TopicDao, String>{
}
