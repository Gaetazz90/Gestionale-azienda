package com.example.gestionaleAzienda.domain.dto.response;

import com.example.gestionaleAzienda.domain.dto.request.create.EntityIdRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PosizioneLavorativaResponse(
        String nome,
        String descrizione,
        String dipartimento
) {
}
