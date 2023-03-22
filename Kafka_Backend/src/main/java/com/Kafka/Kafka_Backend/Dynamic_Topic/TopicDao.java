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

    public TopicDao(int cust_id, String topic_name, int partitions, int replicationFactor) {
        Cust_id = cust_id;
        this.topic_name = topic_name;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    public TopicDao() {

    }

    public int getCust_id() {
        return Cust_id;
    }

    public void setCust_id(int cust_id) {
        Cust_id = cust_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public int getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(int replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    @Override
    public String toString() {
        return "TopicDao{" +
                "Cust_id=" + Cust_id +
                ", topic_name='" + topic_name + '\'' +
                ", partitions=" + partitions +
                ", replicationFactor=" + replicationFactor +
                '}';
    }
}
