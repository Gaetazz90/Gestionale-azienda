package com.example.gestionaleAzienda.domain.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NewsResponse(
                                Long id,
                                String titolo,
                                String contenuto,
                                Long idDipendente,
                                String immagine) {
}
