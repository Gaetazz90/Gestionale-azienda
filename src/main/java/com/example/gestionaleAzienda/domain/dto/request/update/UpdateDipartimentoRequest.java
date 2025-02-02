package com.example.gestionaleAzienda.domain.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateDipartimentoRequest(

        String nome,
        String descrizione
) {
}
