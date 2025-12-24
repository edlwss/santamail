package ru.itche.lettersproccesing.controller.auth.payload;

public record AuthRequest(
        String login,
        String password
) {
}
