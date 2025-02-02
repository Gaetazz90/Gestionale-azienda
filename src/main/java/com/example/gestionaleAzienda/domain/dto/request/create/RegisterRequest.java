package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(
        @NotBlank(message = "Il nome non può essere blank o null")
        String nome,

        @NotBlank(message = "Il cognome non può essere blank o null")
        String cognome,

        @Email(message = "Email non valida")
        String email,

        @NotBlank(message = "inserisci una passsword")
        String password,

        @Past(message = "La data di nascita deve essere nel passato")
        LocalDate dataNascita,

        @NotNull(message = "il comune deve essere presente")
        EntityIdRequest comune_id,

        @NotNull
        EntityIdRequest posizione_lavorativa_id,

        @Pattern(
                regexp = "^\\+([1-9]{1,4})(\\d{1,4})(\\d{1,4})(\\d{1,4})$",
                message = "Telefono non valido")
        String telefono


) {
}
