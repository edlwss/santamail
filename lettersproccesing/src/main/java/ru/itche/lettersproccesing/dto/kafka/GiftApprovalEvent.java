package ru.itche.lettersproccesing.dto.kafka;

public record GiftApprovalEvent(
        Long id,
        String nameGift,
        Double price
) {}

