package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NewsRequest(

        @NotBlank
        String titolo,
        @NotBlank
        String contenuto,
        @NotNull
        Long idDipendente,
        String immagine
) {
}
