package com.example.KafkaBackendFinal.Producer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface KafkaProducerService {
    public void sendMessage(@PathVariable String topic, @RequestBody UserDao userDao);


}
