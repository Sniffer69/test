package com.example.KafkaBackendFinal.Producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService{
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaRepository kafkaRepository;
    private UserDao userDao;
    public void sendMessage(String topic, UserDao userDao) {
        kafkaTemplate.send(topic, userDao.toString());
        LOGGER.info(String.format("This is produced Message %s",userDao,"To Topic %s",topic));
    }


}
