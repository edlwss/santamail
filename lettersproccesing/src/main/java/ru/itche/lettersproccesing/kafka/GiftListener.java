package ru.itche.lettersproccesing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itche.lettersproccesing.dto.kafka.CreateOrderEvent;
import ru.itche.lettersproccesing.dto.kafka.GiftNotFoundEvent;
import ru.itche.lettersproccesing.dto.letter.UpdateLetterRequest;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;
import ru.itche.lettersproccesing.service.letter.LetterService;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftListener {

    private final LetterService letterService;

    @KafkaListener(
            topics = "gift-not-found",
            groupId = "letter-group",
            containerFactory = "giftNotFoundFactory"
    )
    public void handGiftNotFound(GiftNotFoundEvent event) {
        log.info("Получено событие GiftNotFoundEvent, letterId={}", event.letterId());
        letterService.updateStatusLetter(event.letterId(), new UpdateLetterRequest(EnumLetterStatus.REJECTED));
    }

    @KafkaListener(
            topics = "create-order",
            groupId = "letter-group",
            containerFactory = "createOrderFactory"
    )
    public void handleOrderCreated(CreateOrderEvent event) {
        log.info("Получено событиеCreateOrderEvent, letterId={}", event.letterId());
        letterService.updateStatusLetter(event.letterId(), new UpdateLetterRequest(EnumLetterStatus.IN_PROGRESS));
    }
}