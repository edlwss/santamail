package ru.itche.lettersproccesing.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.itche.lettersproccesing.dto.kafka.GiftApprovalEvent;

@Service
@RequiredArgsConstructor
public class GiftApprovalProducer {

    private final KafkaTemplate<String, GiftApprovalEvent> kafkaTemplate;

    public void sendExpensiveGiftEvent(GiftApprovalEvent event) {
        kafkaTemplate.send("gift-expensive", event);
    }

}

