package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.BadgeRequest;
import com.example.gestionaleAzienda.domain.dto.response.BadgeResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Badge;
import com.example.gestionaleAzienda.services.DipendenteService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class BadgeMapper {

    @Autowired
    private DipendenteService dipendenteService;

    public Badge assegnaBadge(BadgeRequest request){
        return Badge.builder()
                .dipendente(dipendenteService.getById(request.dipendente_id().id()))
                .orarioInizio(request.orarioInizio())
                .inizioPausa(request.inizioPausa())
                .finePausa(request.finePausa())
                .orarioFine(request.orarioFine())
                .build();
    }

    public BadgeResponse badge(Badge request){
        return BadgeResponse.builder()
                .idDipendente(dipendenteService.getId(request.getDipendente().getId()))
                .nomeDipendente(dipendenteService.getNome(request.getDipendente().getId()))
                .build();
    }
}
