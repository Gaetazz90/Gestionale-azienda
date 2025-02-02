package com.example.gestionaleAzienda.handler;

import com.example.gestionaleAzienda.domain.dto.response.ErrorResponse;
import com.example.gestionaleAzienda.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(MyEntityNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(MyEntityNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalTimeException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalTimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(IllegalTimeException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NotLikeException.class)
    public ResponseEntity<ErrorResponse> handle(NotLikeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(NotLikeException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<ErrorResponse> handle(NotMatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(NotMatchException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(BadgeAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> handle(BadgeAlreadyAssignedException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(BadgeAlreadyAssignedException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

}
