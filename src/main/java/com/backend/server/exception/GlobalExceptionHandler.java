package com.backend.server.exception;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // AMQP Bağlantı Hatası
    @ExceptionHandler(AmqpConnectException.class)
    public ResponseEntity<String> handleAmqpConnectException(AmqpConnectException e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("RabbitMQ bağlantı hatası: " + e.getMessage());
    }

    // Mesaj Dönüşüm Hatası
    @ExceptionHandler(MessageConversionException.class)
    public ResponseEntity<String> handleMessageConversionException(MessageConversionException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Mesaj dönüşüm hatası: " + e.getMessage());
    }

    // Diğer AMQP Hataları
    @ExceptionHandler(AmqpException.class)
    public ResponseEntity<String> handleAmqpException(AmqpException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("AMQP hatası: " + e.getMessage());
    }

    // Genel Hata
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Genel hata: " + e.getMessage());
    }
}
