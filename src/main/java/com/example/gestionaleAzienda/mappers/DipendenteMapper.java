package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.services.ComuneService;
import com.example.gestionaleAzienda.services.PosizioneLavorativaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class DipendenteMapper {

    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;
    @Autowired
    private ComuneService comuneService;


    public Dipendente inserisciDipendente(DipendenteRequest request) throws MyEntityNotFoundException {
        return Dipendente.builder()
                .nome(request.nome())
                .cognome(request.cognome())
                .dataNascita(request.dataNascita())
                .luogoNascita(comuneService.getById(request.luogo_nascita_id().id()))
                .email(request.email())
                .password(request.password())
                .telefono(request.telefono())
                .immagineProfilo(request.immagineProfilo())
                .posizioneLavorativa(posizioneLavorativaService.getById(request.posizione_lavorativa_id().id()))
                .build();
    }

    public DipendenteResponse profiloDipendente(Dipendente dipendente) {
        return DipendenteResponse.builder()
                .nome(dipendente.getNome())
                .cognome(dipendente.getCognome())
                .dataNascita(dipendente.getDataNascita())
                .email(dipendente.getEmail())
                .telefono(dipendente.getTelefono())
                .luogo_nascita((dipendente.getLuogoNascita().getNome()))
                .posizioneLavorativa(dipendente.getPosizioneLavorativa().getNome())
                .build();
    }


}
