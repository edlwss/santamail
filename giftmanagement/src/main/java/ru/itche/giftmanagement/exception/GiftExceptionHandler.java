package ru.itche.giftmanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itche.giftmanagement.dto.error.ApiError;

@RestControllerAdvice
public class GiftExceptionHandler {

    @ExceptionHandler(LetterUnavailableException.class)
    public ResponseEntity<ApiError> handleLetterUnavailable(
            LetterUnavailableException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler(GiftNotFoundException.class)
    public ResponseEntity<ApiError> handleGiftNotFound(
            GiftNotFoundException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler(GiftOutOfStockException.class)
    public ResponseEntity<ApiError> handleGiftOutOfStock(
            GiftOutOfStockException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleOrderNotFound(
            OrderNotFoundException ex
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(ex.getMessage()));
    }
}
