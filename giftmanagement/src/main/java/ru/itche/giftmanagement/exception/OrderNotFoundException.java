package ru.itche.giftmanagement.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Заказ с id " + id + " не найден");
    }
}
