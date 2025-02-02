package com.example.gestionaleAzienda.domain.dto.request.update;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdateComunicazioneSchedulataRequest(

        String titolo,
        String contenuto,
        String immagine,
        Long utente_id,
        @Future
        LocalDateTime publishTime
) {
}
