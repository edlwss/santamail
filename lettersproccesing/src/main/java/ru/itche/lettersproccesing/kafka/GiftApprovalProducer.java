package ru.itche.lettersproccesing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.itche.lettersproccesing.dto.kafka.GiftApprovalEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftApprovalProducer {

    private final KafkaTemplate<String, GiftApprovalEvent> kafkaTemplate;

    public void sendExpensiveGiftEvent(GiftApprovalEvent event) {
        log.info("Sending GiftApprovalEvent: {}", event);
        kafkaTemplate.send("gift-expensive", event);
    }

}

