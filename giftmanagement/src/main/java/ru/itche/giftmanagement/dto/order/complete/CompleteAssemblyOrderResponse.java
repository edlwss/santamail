package ru.itche.giftmanagement.dto.order.complete;

import ru.itche.giftmanagement.entity.AssemblyOrderStatus;

public record CompleteAssemblyOrderResponse(
        Long orderId,
        AssemblyOrderStatus status
) {
}
