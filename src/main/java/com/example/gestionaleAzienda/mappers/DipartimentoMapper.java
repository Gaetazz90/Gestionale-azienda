package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.DipartimentoRequest;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.services.DipartimentoService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class DipartimentoMapper {

    public Dipartimento fromDipartimentoRequest(DipartimentoRequest request){
        return Dipartimento.builder()
                .nome(request.nome())
                .descrizione(request.descrizione())
                .build();
    }


}
