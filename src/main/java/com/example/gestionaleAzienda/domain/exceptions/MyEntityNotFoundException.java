package com.example.gestionaleAzienda.domain.exceptions;

public class MyEntityNotFoundException extends RuntimeException {

    public MyEntityNotFoundException(String message) {
        super(message);
    }

}
