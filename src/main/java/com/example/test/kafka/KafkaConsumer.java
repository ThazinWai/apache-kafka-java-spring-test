package com.example.test.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test_topic", groupId = "springboot-group-1")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}

