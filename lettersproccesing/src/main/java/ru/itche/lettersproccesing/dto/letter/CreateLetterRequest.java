package ru.itche.lettersproccesing.dto.letter;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.itche.lettersproccesing.dto.gift.CreateGiftRequest;

import java.util.List;

@Schema(description = "Создание письма")
public record CreateLetterRequest(

        @Schema(example = "Иванов")
        String lastName,
        @Schema(example = "Иван")
        String firstName,
        @Schema(example = "Иванович")
        String patronymic,

        @Schema(example = "5")
        Integer age,
        @Schema(example = "Москва")
        String city,
        @Schema(example = "Дорого Дед Мороз! Я вел себя хорошо и хочу очень-очень много подарков за это:")
        String textLetter,

        @Schema(description = "Список подарков")
        List<CreateGiftRequest> gifts
) {
}
