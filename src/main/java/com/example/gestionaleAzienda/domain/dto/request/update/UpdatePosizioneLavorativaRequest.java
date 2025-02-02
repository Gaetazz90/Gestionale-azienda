package com.example.gestionaleAzienda.domain.dto.request.update;

import lombok.Builder;

@Builder
public record UpdatePosizioneLavorativaRequest(

        String nome,
        String descrizione
) {
}
