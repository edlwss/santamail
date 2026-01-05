package ru.itche.giftmanagement.dto.order.status;

import ru.itche.giftmanagement.entity.AssemblyOrderStatus;

public record ChangeAssemblyOrderStatusResponse(
        Long orderId,
        AssemblyOrderStatus oldStatus,
        AssemblyOrderStatus newStatus
) {
}

