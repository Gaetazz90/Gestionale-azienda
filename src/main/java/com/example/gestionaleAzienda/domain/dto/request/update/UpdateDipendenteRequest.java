package com.example.gestionaleAzienda.domain.dto.request.update;

import com.example.gestionaleAzienda.domain.dto.request.create.EntityIdRequest;
import com.example.gestionaleAzienda.domain.entities.Comune;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdateDipendenteRequest(

        String nome,
        String cognome,
        @Past
        LocalDate dataNascita,
        EntityIdRequest luogo_nascita_id,
        @Email
        String email,
        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$", message = "Formato password non valido: almeno 8 caratteri, 1 maiuscola, 1 numero, 1 carattere speciale")
        String password,
        @Pattern(regexp = "^\\+([1-9]{1,4})(\\d{1,4})(\\d{1,4})(\\d{1,4})$", message = "Formato telefono non valido")
        String telefono,
        String immagineProfilo,
        EntityIdRequest posizione_lavorativa_id
) {
}
