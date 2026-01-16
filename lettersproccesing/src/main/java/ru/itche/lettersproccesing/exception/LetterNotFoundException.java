package ru.itche.lettersproccesing.exception;

public class LetterNotFoundException extends RuntimeException {

    public LetterNotFoundException(Long letterId) {
        super("Письмо с id " + letterId + " не найдено");
    }
}
