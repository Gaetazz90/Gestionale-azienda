package com.example.gestionaleAzienda.domain.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record DipendenteResponse(
        String nome,
        String cognome,
        LocalDate dataNascita,
        String email,
        String telefono,
        String luogo_nascita,
        String posizioneLavorativa
) {
}
