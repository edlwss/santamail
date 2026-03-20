package ru.itche.giftmanagement.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import ru.itche.giftmanagement.client.LetterClient;
import ru.itche.giftmanagement.dto.kafka.CreateOrderEvent;
import ru.itche.giftmanagement.dto.kafka.GiftNotFoundEvent;
import ru.itche.giftmanagement.dto.letter.GetLetter;
import ru.itche.giftmanagement.entity.AssemblyOrder;
import ru.itche.giftmanagement.entity.AssemblyOrderItem;
import ru.itche.giftmanagement.entity.AssemblyOrderStatus;
import ru.itche.giftmanagement.entity.GiftCatalog;
import ru.itche.giftmanagement.exception.GiftNotFoundException;
import ru.itche.giftmanagement.exception.GiftOutOfStockException;
import ru.itche.giftmanagement.exception.LetterUnavailableException;
import ru.itche.giftmanagement.exception.OrderNotFoundException;
import ru.itche.giftmanagement.kafka.GiftEventProducer;
import ru.itche.giftmanagement.repository.AssemblyOrderRepository;
import ru.itche.giftmanagement.repository.GiftCatalogRepository;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AssemblyOrderService {

    private final AssemblyOrderRepository assemblyOrderRepository;
    private final GiftCatalogRepository giftCatalogRepository;
    private final LetterClient letterClient;
    private final GiftEventProducer giftEventProducer;

    @Transactional
    public void createOrderFromLetter(Long letterId) {

        GetLetter letter;
        try {
            letter = letterClient.getLetterById(letterId);
        } catch (RestClientException e) {
            throw new LetterUnavailableException(letterId);
        }

        List<String> requestedNames = letter.gifts().stream()
                .map(g -> g.nameGift().toLowerCase())
                .toList();

        List<GiftCatalog> gifts = giftCatalogRepository.findAllByNameIgnoreCase(requestedNames);

        if (gifts.size() != requestedNames.size()) {
            throw new GiftNotFoundException("Некоторые позиции отсутствуют в каталоге");
        }

        for (GiftCatalog gift : gifts) {
            if (gift.getAvailable() < 1) {
                throw new GiftOutOfStockException("Подарков нет в наличии: " + gift.getName());
            }
        }

        AssemblyOrder order = new AssemblyOrder();
        order.setLetterId(letter.id());
        order.setStatus(AssemblyOrderStatus.CREATED);

        for (GiftCatalog gift : gifts) {
            gift.setStockReserved(gift.getStockReserved() + 1);

            AssemblyOrderItem item = new AssemblyOrderItem();
            item.setOrder(order);
            item.setGift(gift);
            item.setQuantity(1);

            order.getItems().add(item);
        }

        AssemblyOrder savedOrder = assemblyOrderRepository.save(order);

        CreateOrderEvent event = new CreateOrderEvent(
                savedOrder.getLetterId()
        );

        registerAfterCommit(() -> giftEventProducer.sendCreateOrderEvent(event));
    }

    private void registerAfterCommit(Runnable action) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                action.run();
            }
        });
    }

    @Transactional
    public void completeOrder(Long letterId) {

        AssemblyOrder order = assemblyOrderRepository.findByLetterId(letterId)
                .orElseThrow(() -> new OrderNotFoundException(letterId));

        for (AssemblyOrderItem item : order.getItems()) {
            GiftCatalog gift = item.getGift();
            int qty = item.getQuantity();
            gift.setStockReserved(gift.getStockReserved() - qty);
            gift.setStockTotal(gift.getStockTotal() - qty);
        }
        order.setStatus(AssemblyOrderStatus.DONE);

        assemblyOrderRepository.save(order);
    }

}
