package com.example.KafkaBackendFinal.Producer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KafkaRepository extends JpaRepository <UserDao, String> {

}
