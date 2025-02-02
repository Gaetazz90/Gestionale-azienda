package com.example.gestionaleAzienda.domain.exceptions;

public class BadgeAlreadyAssignedException extends RuntimeException {
    public BadgeAlreadyAssignedException(String message) {
        super(message);
    }
}
