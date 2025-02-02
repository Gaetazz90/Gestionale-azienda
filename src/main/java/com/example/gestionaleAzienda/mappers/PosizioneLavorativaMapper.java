package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.DipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.PosizioneLavorativaRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.PosizioneLavorativaResponse;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.entities.PosizioneLavorativa;
import com.example.gestionaleAzienda.repositories.PosizioneLavorativaRepository;
import com.example.gestionaleAzienda.services.DipartimentoService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class PosizioneLavorativaMapper {

    @Autowired
    private DipartimentoService dipartimentoService;

    public PosizioneLavorativa fromPosizioneLavorativaRequest(PosizioneLavorativaRequest request){
        return PosizioneLavorativa.builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .dipartimento(dipartimentoService.getById(request.dipartimento_id().id()))
                .build();
    }

    public PosizioneLavorativaResponse getPosizione(PosizioneLavorativa posizione) {
        return PosizioneLavorativaResponse.builder()
                .nome(posizione.getNome())
                .descrizione(posizione.getDescrizione())
                .dipartimento(posizione.getDipartimento().getNome())
                .build();
    }
}
