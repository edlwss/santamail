package ru.itche.lettersproccesing.dto.gift;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Подарок в письме")
public record CreateGiftRequest(

        @Schema(example = "Машинка")
        String nameGift,

        @Schema(example = "1050.23")
        Double price
) {
}
