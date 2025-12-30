package ru.itche.giftmanagement.dto.order.create;

public record OrderItemResponseDto(
        Long giftId,
        String giftName,
        Integer quantity
) {
}
