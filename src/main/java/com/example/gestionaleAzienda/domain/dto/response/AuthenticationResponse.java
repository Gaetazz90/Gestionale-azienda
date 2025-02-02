package com.example.gestionaleAzienda.domain.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}
