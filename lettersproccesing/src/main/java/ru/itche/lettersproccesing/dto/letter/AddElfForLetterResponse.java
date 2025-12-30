package ru.itche.lettersproccesing.dto.letter;

import org.apache.kafka.common.protocol.types.Field;
import ru.itche.lettersproccesing.entity.Elf;

public record AddElfForLetterResponse(
        Long idLetter,
        String nameElf
) {
}
