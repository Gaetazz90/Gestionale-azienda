package com.example.gestionaleAzienda.domain.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


public record CommentoRequest (

        @NotBlank
        String contenuto,
        @NotNull
        EntityIdRequest utente_id,
        @NotNull
        EntityIdRequest news_id



){
}
