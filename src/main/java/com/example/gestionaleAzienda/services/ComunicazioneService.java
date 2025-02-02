package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.DipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.enums.Role;
import com.example.gestionaleAzienda.domain.exceptions.ComunicazioneNotFoundException;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.ComunicazioneMapper;
import com.example.gestionaleAzienda.mappers.DipartimentoMapper;
import com.example.gestionaleAzienda.repositories.ComunicazioneRepository;
import com.example.gestionaleAzienda.repositories.DipartimentoRepository;
import com.example.gestionaleAzienda.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComunicazioneService {

    @Autowired
    private ComunicazioneRepository comunicazioneRepository;
    @Autowired
    private ComunicazioneMapper comunicazioneMapper;
    @Autowired
    private DipendenteService dipendenteService;

    public Comunicazione getById(Long id){
        return comunicazioneRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Comunicazione con id " + id + " non trovata"));
    }

    public List<Comunicazione> getAllByIdUtente(Long id){
        List<Comunicazione> comunicazioni = comunicazioneRepository.findAllByIdDipendente(id);

        if (comunicazioni.isEmpty()) {
            throw new ComunicazioneNotFoundException("Nessuna comunicazione trovata per il dipendente con ID: " + id);
        }

        return comunicazioni;
    }

    public List<Comunicazione> getAll(){
        return comunicazioneRepository.findAll();
    }

    public void deleteById(Long id) {
        comunicazioneRepository.deleteById(id);
    }

    public EntityIdResponse creaComunicazione(ComunicazioneRequest request) throws MyEntityNotFoundException {
        // Verifico che l'utente esista e lo prendo
        Dipendente dip = dipendenteService.getById(request.utente_id().id());

        Comunicazione comunicazione = comunicazioneRepository.save(Comunicazione
                .builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .immagine(request.immagine())
                .dipendente(dipendenteService.getById(request.utente_id().id()))
                .build());
        return EntityIdResponse.builder().id(comunicazione.getId()).build();
    }

    public EntityIdResponse updateComunicazione(Long id, UpdateComunicazioneRequest request){
        Comunicazione comunicazione = getById(id);
        if (request.titolo() != null) comunicazione.setTitolo(request.titolo());
        if(request.contenuto() != null) comunicazione.setContenuto(request.contenuto());
        if(request.immagine() != null) comunicazione.setImmagine(request.immagine());

        return new EntityIdResponse(comunicazioneRepository.save(comunicazione).getId());
    }
}
