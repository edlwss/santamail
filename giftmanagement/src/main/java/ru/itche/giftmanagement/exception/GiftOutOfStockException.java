package ru.itche.giftmanagement.exception;

public class GiftOutOfStockException extends RuntimeException {

    public GiftOutOfStockException(String name) {
        super("Подарок отсутствует на складе: " + name);
    }
}
