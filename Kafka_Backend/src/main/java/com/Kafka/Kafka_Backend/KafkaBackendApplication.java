package com.Kafka.Kafka_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@SpringBootApplication
@EntityScan
//@EnableJpaRepositories(basePackages = "com.Kafka")
public class KafkaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBackendApplication.class, args);
	}

}
