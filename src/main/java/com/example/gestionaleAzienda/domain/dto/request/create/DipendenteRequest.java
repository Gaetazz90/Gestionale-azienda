package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DipendenteRequest(
        @NotBlank
        String nome,
        @NotBlank
        String cognome,
        @Past
        LocalDate dataNascita,
        @NotNull
        EntityIdRequest luogo_nascita_id,
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$", message = "Formato password non valido: almeno 8 caratteri, 1 maiuscola, 1 numero, 1 carattere speciale")
        String password,
        @Pattern(regexp = "^\\+([1-9]{1,4})(\\d{1,4})(\\d{1,4})(\\d{1,4})$", message = "Formato telefono non valido")
        String telefono,
        @NotBlank
        String immagineProfilo,
        @NotNull
        EntityIdRequest posizione_lavorativa_id
) {
}
