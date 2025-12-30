package ru.itche.lettersproccesing.entity;

public enum EnumLetterStatus {
    RECEIVED,      // получено
    IN_PROGRESS,   // в обработке
    DONE,          // исполнено
    REJECTED,       // отклонено
    WAITING_APPROVAL // ожидает подтверждение
}

