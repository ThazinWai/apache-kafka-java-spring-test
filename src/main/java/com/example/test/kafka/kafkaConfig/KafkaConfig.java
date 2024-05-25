package com.example.test.kafka.kafkaConfig;

import com.example.test.kafka.model.OrderModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, OrderModel> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-12576z.us-west2.gcp.confluent.cloud:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configProps.put("security.protocol", "SASL_SSL");
        configProps.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"AMZTQZAYBJNMWE4C\" password=\"GPiQLQelOU46L4gr1TUT5CLMF2Qk4sld8isBSLWhS9yieYUi+4+2AyIFJ5XiBmNp\";");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, OrderModel> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, OrderModel> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-12576z.us-west2.gcp.confluent.cloud:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "order-group");
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.test.kafka.model");
        configProps.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        configProps.put("security.protocol", "SASL_SSL");
        configProps.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"AMZTQZAYBJNMWE4C\" password=\"GPiQLQelOU46L4gr1TUT5CLMF2Qk4sld8isBSLWhS9yieYUi+4+2AyIFJ5XiBmNp\";");
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(OrderModel.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
