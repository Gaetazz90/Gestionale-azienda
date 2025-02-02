package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record LikeRequest(
                            @NonNull
                            Long dipendente_id,
                            @NotNull
                            Long news_id) {
}
