package com.example.gestionaleAzienda.domain.dto.request.update;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String oldPassword,
        String newPassword
) {
}
