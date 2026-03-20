package ru.itche.lettersproccesing.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.itche.lettersproccesing.dto.kafka.CreateOrderEvent;
import ru.itche.lettersproccesing.dto.kafka.GiftNotFoundEvent;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean(name = "giftNotFoundFactory")
    public ConcurrentKafkaListenerContainerFactory<String, GiftNotFoundEvent> giftNotFoundFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, GiftNotFoundEvent>();

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties(null));

        JsonDeserializer<GiftNotFoundEvent> valueDeserializer = new JsonDeserializer<>(GiftNotFoundEvent.class);
        valueDeserializer.addTrustedPackages("ru.itche.lettersproccesing.dto.kafka");
        valueDeserializer.setUseTypeHeaders(false);

        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                valueDeserializer
        ));
        return factory;
    }

    @Bean(name = "createOrderFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CreateOrderEvent> createOrderFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CreateOrderEvent>();

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties(null));

        JsonDeserializer<CreateOrderEvent> valueDeserializer = new JsonDeserializer<>(CreateOrderEvent.class);
        valueDeserializer.addTrustedPackages("ru.itche.lettersproccesing.dto.kafka");
        valueDeserializer.setUseTypeHeaders(false);

        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                valueDeserializer
        ));
        return factory;
    }

}


