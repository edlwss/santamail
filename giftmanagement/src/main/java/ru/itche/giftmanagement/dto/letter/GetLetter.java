package ru.itche.giftmanagement.dto.letter;

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
