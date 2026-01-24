package ru.itche.giftmanagement.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.itche.giftmanagement.dto.kafka.AddElfForLetterEvent;
import ru.itche.giftmanagement.dto.kafka.CreateLetterEvent;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean(name = "createLetterFactory")
    public ConcurrentKafkaListenerContainerFactory<String, CreateLetterEvent> createLetterFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, CreateLetterEvent>();

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties(null));

        JsonDeserializer<CreateLetterEvent> valueDeserializer = new JsonDeserializer<>(CreateLetterEvent.class);
        valueDeserializer.addTrustedPackages("ru.itche.giftmanagement.dto.kafka");
        valueDeserializer.setUseTypeHeaders(false);

        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                valueDeserializer
        ));
        return factory;
    }

    @Bean(name = "addElfFactory")
    public ConcurrentKafkaListenerContainerFactory<String, AddElfForLetterEvent> addElfFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, AddElfForLetterEvent>();

        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties(null));

        JsonDeserializer<AddElfForLetterEvent> valueDeserializer = new JsonDeserializer<>(AddElfForLetterEvent.class);
        valueDeserializer.addTrustedPackages("ru.itche.giftmanagement.dto.kafka");
        valueDeserializer.setUseTypeHeaders(false);

        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                valueDeserializer
        ));
        return factory;
    }
}


