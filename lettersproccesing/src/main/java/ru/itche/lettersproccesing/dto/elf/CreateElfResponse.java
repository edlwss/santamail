package ru.itche.lettersproccesing.dto.elf;

public record CreateElfResponse(
        Long id,
        String login,
        String name,
        String role) {
}
