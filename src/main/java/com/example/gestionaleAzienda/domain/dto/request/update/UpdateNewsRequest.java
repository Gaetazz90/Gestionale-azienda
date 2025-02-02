package com.example.gestionaleAzienda.domain.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateNewsRequest(@NotBlank
                                String titolo,
                                @NotBlank
                                String contenuto,
                                String immagine) {
}
