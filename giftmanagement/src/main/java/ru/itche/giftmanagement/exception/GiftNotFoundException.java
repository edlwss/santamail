package ru.itche.giftmanagement.exception;

public class GiftNotFoundException extends RuntimeException {

    public GiftNotFoundException(String name) {
        super("Подарок отсутствует в каталоге: " + name);
    }
}
