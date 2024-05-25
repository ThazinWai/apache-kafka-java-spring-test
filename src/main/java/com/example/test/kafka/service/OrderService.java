package com.example.test.kafka.service;

import com.example.test.kafka.kafkaConfig.KafkaOrderProducer;
import com.example.test.kafka.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final KafkaOrderProducer kafkaOrderProducer;

    @Autowired
    public OrderService(KafkaOrderProducer kafkaOrderProducer) {
        this.kafkaOrderProducer = kafkaOrderProducer;
    }

    public OrderModel createOrder(OrderModel orderModel) {
        kafkaOrderProducer.sendOrder(orderModel);
        return orderModel;
    }
}
