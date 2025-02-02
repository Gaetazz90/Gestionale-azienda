package com.example.gestionaleAzienda.domain.dto.request.create;

import lombok.Builder;

@Builder
public record EntityIdRequest(
        Long id
) {
}
