package com.example.KafkaBackendFinal;

import com.example.KafkaBackendFinal.Listner.KafkaConsumerListnerImpl;
import com.example.KafkaBackendFinal.Producer.KafkaProducerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaBackendFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBackendFinalApplication.class, args);
	}


}
