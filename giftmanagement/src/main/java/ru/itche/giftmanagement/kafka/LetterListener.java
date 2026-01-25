package ru.itche.giftmanagement.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.itche.giftmanagement.dto.kafka.AddElfForLetterEvent;
import ru.itche.giftmanagement.dto.kafka.CreateLetterEvent;
import ru.itche.giftmanagement.dto.kafka.GiftNotFoundEvent;
import ru.itche.giftmanagement.exception.GiftNotFoundException;
import ru.itche.giftmanagement.exception.GiftOutOfStockException;
import ru.itche.giftmanagement.service.order.AssemblyOrderService;

@Slf4j
@Component
@RequiredArgsConstructor
public class LetterListener {

    private final AssemblyOrderService assemblyOrderService;
    private final GiftEventProducer giftEventProducer;

    @KafkaListener(
            topics = "letter-create",
            groupId = "giftmanag-group",
            containerFactory="createLetterFactory"
    )
    public void handleLetterCreated(CreateLetterEvent event) {
        log.info("Получено событие CreateLetterEvent, letterId={}", event.letterId());
        try {
            assemblyOrderService.createOrderFromLetter(event.letterId());
        } catch (GiftNotFoundException | GiftOutOfStockException ex) {
            log.warn("Не удалось создать заказ для letterId={}: {}", event.letterId(), ex.getMessage());
            giftEventProducer.sendGiftNotFoundEvent(new GiftNotFoundEvent(event.letterId()));
        }
    }

    @KafkaListener(
            topics = "add-elf-for-letter",
            groupId = "giftmanag-group",
            containerFactory="addElfFactory"
    )
    public void handleAddElfForLetter(AddElfForLetterEvent event) {
        log.info("Получено событие AddElfForLetterEvent, letterId={}", event.letterId());
        assemblyOrderService.completeOrder(event.letterId());
    }
}

