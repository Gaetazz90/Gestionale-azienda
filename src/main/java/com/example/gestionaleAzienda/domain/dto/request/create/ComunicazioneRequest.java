package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ComunicazioneRequest(

        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        String immagine,
        @NotNull
        EntityIdRequest utente_id

) {
}
