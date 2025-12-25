package ru.itche.lettersproccesing.dto.user;

public record CreateElfResponse(
        Long id,
        String login,
        String name,
        String role) {
}
