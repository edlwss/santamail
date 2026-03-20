package ru.itche.giftmanagement.exception;

public class LetterUnavailableException extends RuntimeException{

    public LetterUnavailableException(Long letterId) {
        super("Письмо с id " + letterId + " недоступно");
    }
}
