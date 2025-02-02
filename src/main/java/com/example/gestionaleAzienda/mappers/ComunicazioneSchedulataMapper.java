package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneSchedulataRequest;
import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import com.example.gestionaleAzienda.domain.entities.ComunicazioneSchedulata;
import com.example.gestionaleAzienda.services.DipendenteService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class ComunicazioneSchedulataMapper {

    @Autowired
    private DipendenteService dipendenteService;

    public ComunicazioneSchedulata fromComunicazioneSchedulataRequest(ComunicazioneSchedulataRequest request){
        return ComunicazioneSchedulata.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .dipendente(dipendenteService.getById(request.utente_id().id()))
                .publishTime(request.publishTime())
                .build();
    }
}
