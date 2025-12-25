package ru.itche.lettersproccesing.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание user")
public record CreateElfRequest(
        @Schema(example = "elf1")
        String login,

        @Schema(example = "12345abc!")
        String password,

        @Schema(example = "Снежок")
        String nameElf
) {}

