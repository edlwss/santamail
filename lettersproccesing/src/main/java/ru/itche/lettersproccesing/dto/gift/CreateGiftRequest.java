package ru.itche.lettersproccesing.dto.gift;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Подарок в письме")
public record CreateGiftRequest(
        String nameGift,
        Double price
) {
}
