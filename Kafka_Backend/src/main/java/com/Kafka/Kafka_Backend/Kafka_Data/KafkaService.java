package com.Kafka.Kafka_Backend.Kafka_Data;

import java.util.List;
public interface KafkaService {
KafkaUserDao insert (KafkaUserDao userDao );
List<KafkaUserDao> getAll();
int deleteById (String id);

}
