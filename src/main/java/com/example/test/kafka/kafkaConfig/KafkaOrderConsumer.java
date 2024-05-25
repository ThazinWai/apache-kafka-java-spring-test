package com.example.test.kafka.kafkaConfig;

import com.example.test.kafka.model.OrderModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderConsumer {

    @KafkaListener(topics = "orders_topic", groupId = "order-group")
    public void consume(OrderModel orderModel) {
        orderModel.setStatus("Processed");
        // Perform any additional logic here
        System.out.println("Consumed order: " + orderModel);
    }
}
