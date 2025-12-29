package ru.itche.lettersproccesing.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Авторизация")
public record AuthRequest(

        @Schema(example = "admin")
        String login,

        @Schema(example = "1111")
        String password
) {
}
