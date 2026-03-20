package ru.itche.giftmanagement.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.itche.giftmanagement.dto.kafka.CreateOrderEvent;
import ru.itche.giftmanagement.dto.kafka.GiftNotFoundEvent;
import ru.itche.giftmanagement.exception.GiftNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftEventProducer {

    private final KafkaTemplate<String, GiftNotFoundEvent> giftNotFoundkafkaTemplate;
    private final KafkaTemplate<String, CreateOrderEvent> createOrderkafkaTemplate;

    public void sendGiftNotFoundEvent(GiftNotFoundEvent event) {
        giftNotFoundkafkaTemplate.send("gift-not-found", event);
    }

    public void sendCreateOrderEvent(CreateOrderEvent event) {
        createOrderkafkaTemplate.send("create-order", event);
    }
}
