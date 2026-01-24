package ru.itche.lettersproccesing.dto.letter;

import ru.itche.lettersproccesing.entity.EnumLetterStatus;

public record GetLetterFilterRequest(
        String city,
        EnumLetterStatus status
) {
}
