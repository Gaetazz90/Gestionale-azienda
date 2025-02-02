package com.example.gestionaleAzienda.domain.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateComunicazioneRequest(

        String titolo,
        String contenuto,
        String immagine,
        Long utente_id
) {
}
