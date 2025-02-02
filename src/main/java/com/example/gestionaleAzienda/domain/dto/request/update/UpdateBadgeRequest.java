package com.example.gestionaleAzienda.domain.dto.request.update;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UpdateBadgeRequest(

        LocalDateTime inizioPausa,
        LocalDateTime finePausa,
        LocalDateTime orarioFine
) {
}
