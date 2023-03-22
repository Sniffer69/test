package com.Kafka.Kafka_Backend.Dynamic_Topic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TopicDao {
    @Id
    private int Cust_id;
    private String topic_name;
    private int partitions;
    private int replicationFactor;

}
