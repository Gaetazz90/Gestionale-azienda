package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DipartimentoRequest(
        @NotBlank
        String nome,
        @NotBlank
        String descrizione

){
}
