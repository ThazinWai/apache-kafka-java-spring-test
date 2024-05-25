package com.example.test.kafka.kafkaConfig;

import com.example.test.kafka.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderProducer {

    private static final String TOPIC = "orders_topic";

    @Autowired
    private KafkaTemplate<String, OrderModel> kafkaTemplate;

    public void sendOrder(OrderModel orderModel) {
        kafkaTemplate.send(TOPIC, orderModel);
    }
}
