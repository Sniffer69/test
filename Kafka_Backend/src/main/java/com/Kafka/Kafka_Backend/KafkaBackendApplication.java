package com.Kafka.Kafka_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.kafka.core.KafkaAdmin;


@SpringBootApplication
@EntityScan
//@EnableJpaRepositories(basePackages = "com.Kafka")

public class KafkaBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkaBackendApplication.class, args);
	}

}
