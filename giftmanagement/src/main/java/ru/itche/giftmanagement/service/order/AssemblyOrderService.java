package ru.itche.giftmanagement.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import ru.itche.giftmanagement.client.LetterClient;
import ru.itche.giftmanagement.dto.order.complete.CompleteAssemblyOrderRequest;
import ru.itche.giftmanagement.dto.order.complete.CompleteAssemblyOrderResponse;
import ru.itche.giftmanagement.dto.order.create.AssemblyOrderResponse;
import ru.itche.giftmanagement.dto.letter.GetLetter;
import ru.itche.giftmanagement.dto.order.create.OrderItemResponseDto;
import ru.itche.giftmanagement.dto.order.status.ChangeAssemblyOrderStatusRequest;
import ru.itche.giftmanagement.dto.order.status.ChangeAssemblyOrderStatusResponse;
import ru.itche.giftmanagement.entity.AssemblyOrder;
import ru.itche.giftmanagement.entity.AssemblyOrderItem;
import ru.itche.giftmanagement.entity.AssemblyOrderStatus;
import ru.itche.giftmanagement.entity.GiftCatalog;
import ru.itche.giftmanagement.exception.LetterUnavailableException;
import ru.itche.giftmanagement.repository.AssemblyOrderRepository;
import ru.itche.giftmanagement.repository.GiftCatalogRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class AssemblyOrderService {

    private final AssemblyOrderRepository assemblyOrderRepository;
    private final GiftCatalogRepository giftCatalogRepository;
    private final LetterClient letterClient;

    @Transactional
    public AssemblyOrderResponse createOrderFromLetter(Long letterId) {

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
            throw new IllegalStateException("Некоторые позиции отсутствуют в каталоге");
        }

        for (GiftCatalog gift : gifts) {
            if (gift.getAvailable() < 1) {
                throw new IllegalStateException("Подарков нет в наличии: " + gift.getName());
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

        return new AssemblyOrderResponse(
                savedOrder.getId(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream()
                        .map(i -> new OrderItemResponseDto(
                                i.getGift().getId(),
                                i.getGift().getName(),
                                i.getQuantity()
                        ))
                        .toList()
        );
    }

    @Transactional
    public CompleteAssemblyOrderResponse completeOrder(CompleteAssemblyOrderRequest request) {

        AssemblyOrder order = assemblyOrderRepository.findById(request.orderId())
                .orElseThrow(() -> new IllegalStateException("Заказа не существует"));

        for (AssemblyOrderItem item : order.getItems()) {
            GiftCatalog gift = item.getGift();
            int qty = item.getQuantity();
            gift.setStockReserved(gift.getStockReserved() - qty);
            gift.setStockTotal(gift.getStockTotal() - qty);
        }
        order.setStatus(AssemblyOrderStatus.DONE);

        assemblyOrderRepository.save(order);

        return new CompleteAssemblyOrderResponse(
                order.getId(),
                order.getStatus()
        );
    }

    @Transactional
    public ChangeAssemblyOrderStatusResponse changeStatus(
            ChangeAssemblyOrderStatusRequest request) {

        AssemblyOrder order = assemblyOrderRepository.findById(request.orderId())
                .orElseThrow(() -> new IllegalStateException("Заказа не существует"));

        AssemblyOrderStatus oldStatus = order.getStatus();
        AssemblyOrderStatus newStatus = request.newStatus();

        if (oldStatus == AssemblyOrderStatus.CREATED &&
                newStatus == AssemblyOrderStatus.IN_PROGRESS) {

            order.setStatus(AssemblyOrderStatus.IN_PROGRESS);

        } else if (newStatus == AssemblyOrderStatus.CANCELLED) {

            for (AssemblyOrderItem item : order.getItems()) {
                GiftCatalog gift = item.getGift();
                int qty = item.getQuantity();
                gift.setStockReserved(gift.getStockReserved() - qty);
            }
            order.setStatus(AssemblyOrderStatus.CANCELLED);

        }
        assemblyOrderRepository.save(order);

        return new ChangeAssemblyOrderStatusResponse(
                order.getId(),
                oldStatus,
                order.getStatus()
        );
    }
}
