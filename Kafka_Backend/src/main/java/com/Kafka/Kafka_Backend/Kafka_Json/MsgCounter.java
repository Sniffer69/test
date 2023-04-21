//package com.Kafka.Kafka_Backend.Kafka_Json;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Component
//public class MsgCounter {
//    private AtomicInteger counter = new AtomicInteger(0);
//
//    @KafkaListener(topics = "Kafka")
//    public void listen(User message) {
//        counter.incrementAndGet();
//    }
//
//    public int getCount() {
//        return counter.get();
//    }
//}
