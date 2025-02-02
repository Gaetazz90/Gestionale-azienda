package com.example.gestionaleAzienda.domain.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String exception,
        String message
) {
}
