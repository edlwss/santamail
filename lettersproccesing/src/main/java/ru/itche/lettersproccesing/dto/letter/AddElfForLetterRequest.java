package ru.itche.lettersproccesing.dto.letter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Назначение эльфа-куратора для письма")
public record AddElfForLetterRequest(

        @Schema(example = "id эльфа-куратора")
        Long idElf
) {
}
