package ru.itche.lettersproccesing.dto.letter;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.itche.lettersproccesing.entity.EnumLetterStatus;

@Schema(description = "Обновление статуса у письма")
public record UpdateLetterRequest (

        @Schema(example = "DONE/REJECTED")
        EnumLetterStatus statusLetter
){
}
