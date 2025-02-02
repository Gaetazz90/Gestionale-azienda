package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BadgeRequest(

        @NotNull
        EntityIdRequest dipendente_id,
        @NotNull
        LocalDateTime orarioInizio,
        LocalDateTime inizioPausa,
        LocalDateTime finePausa,
        LocalDateTime orarioFine

) {

}
