package ru.itche.lettersproccesing.dto.letter;

import ru.itche.lettersproccesing.dto.gift.GetGift;

import java.util.List;

public record GetLetter(
        Long id,

        String lastName,
        String firstName,
        String patronymic,

        Integer age,
        String city,
        String textLetter,

        List<GetGift> gifts
) {
}
