package com.example.gestionaleAzienda.domain.dto.response;

import lombok.Builder;

@Builder
public record BadgeResponse(

        Long idDipendente,
        String nomeDipendente
) {
}
