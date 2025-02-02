package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PosizioneLavorativaRequest(
        @NotBlank(message = "Il nome non può essere vuoto")
        String nome,
        @NotBlank(message = "Il cognome non può essere vuoto")
        String descrizione,
        @NotNull
        EntityIdRequest dipartimento_id
) {
}
