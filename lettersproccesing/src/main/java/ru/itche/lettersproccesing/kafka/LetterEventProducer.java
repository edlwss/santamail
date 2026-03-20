package ru.itche.lettersproccesing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.itche.lettersproccesing.dto.kafka.AddElfForLetterEvent;
import ru.itche.lettersproccesing.dto.kafka.CreateLetterEvent;
import ru.itche.lettersproccesing.dto.kafka.GiftApprovalEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterEventProducer {

    private final KafkaTemplate<String, GiftApprovalEvent> giftApprovalkafkaTemplate;
    private final KafkaTemplate<String, CreateLetterEvent> letterCreatedKafkaTemplate;
    private final KafkaTemplate<String, AddElfForLetterEvent> addElfForLetterKafkaTemplate;

    public void sendExpensiveGiftEvent(GiftApprovalEvent event) {
        giftApprovalkafkaTemplate.send("gift-expensive", event);
    }

    public void sendCreateLetterEvent(CreateLetterEvent event) {
        letterCreatedKafkaTemplate.send("letter-create", event);
    }

    public void sendAddElfForLetterEvent(AddElfForLetterEvent event) {
        addElfForLetterKafkaTemplate.send("add-elf-for-letter", event);
    }
}

