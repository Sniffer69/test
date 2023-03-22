package com.Kafka.Kafka_Backend.Kafka_Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class KafkaServiceImpl implements KafkaService{

    private static Logger logger= LoggerFactory.getLogger(KafkaService.class);
    private KafkaRepo repo;

    @Override
    public KafkaUserDao insert(KafkaUserDao userDao) {
        return null;
    }

    @Override
    public List<KafkaUserDao> getAll() {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
