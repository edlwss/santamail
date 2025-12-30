package ru.itche.giftmanagement.dto.order.create;

import ru.itche.giftmanagement.entity.AssemblyOrderStatus;

import java.util.List;

public record AssemblyOrderResponse(
        Long orderId,
        AssemblyOrderStatus status,
        List<OrderItemResponseDto> items
) {
}

