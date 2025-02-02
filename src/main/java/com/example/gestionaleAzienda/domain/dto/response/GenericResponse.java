package com.example.gestionaleAzienda.domain.dto.response;

import lombok.Builder;

@Builder
public record GenericResponse(
        String message
) {
}
