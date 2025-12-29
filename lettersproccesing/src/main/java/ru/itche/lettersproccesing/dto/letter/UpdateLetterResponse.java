package ru.itche.lettersproccesing.dto.letter;

import ru.itche.lettersproccesing.entity.EnumLetterStatus;

public record UpdateLetterResponse(
        Long idLetter,
        EnumLetterStatus letterStatus
) {
}
