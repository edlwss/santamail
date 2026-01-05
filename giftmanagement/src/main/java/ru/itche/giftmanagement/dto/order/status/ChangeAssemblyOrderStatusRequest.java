package ru.itche.giftmanagement.dto.order.status;

import ru.itche.giftmanagement.entity.AssemblyOrderStatus;

public record ChangeAssemblyOrderStatusRequest(
        Long orderId,
        AssemblyOrderStatus newStatus
) {
}

