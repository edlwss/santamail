package ru.itche.lettersproccesing.dto.letter;

import ru.itche.lettersproccesing.entity.EnumLetterStatus;

public record CreateLetterResponse(
        Long id,
        String firstName,
        String city,
        int giftsCount,
        EnumLetterStatus statusLetter
) {}

