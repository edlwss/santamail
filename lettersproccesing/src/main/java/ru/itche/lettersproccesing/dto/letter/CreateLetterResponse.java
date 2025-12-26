package ru.itche.lettersproccesing.dto.letter;

public record CreateLetterResponse(
        Long id,
        String firstName,
        String city,
        int giftsCount
) {}

