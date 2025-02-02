package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.DipendenteMapper;
import com.example.gestionaleAzienda.repositories.ComuneRepository;
import com.example.gestionaleAzienda.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private DipendenteMapper dipendenteMapper;
    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;
    @Autowired
    private ComuneService comuneService;

    public Dipendente getById(Long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("utente con id " + id + " non trovato"));
    }

    public List<Dipendente> getAll(){
        return dipendenteRepository.findAll();
    }

    public void deleteById(Long id) {
        dipendenteRepository.deleteById(id);
    }

    public void insertDipendente(Dipendente dipendente)
    {
        dipendenteRepository.save(dipendente);
    }

    public EntityIdResponse creaDipendente(DipendenteRequest request) {
        Dipendente dipendente = dipendenteRepository.save(dipendenteMapper.inserisciDipendente(request));
        return new EntityIdResponse(dipendente.getId());
    }

    public EntityIdResponse updateDipendente(Long id, UpdateDipendenteRequest request) throws MyEntityNotFoundException{
        Dipendente dipendente = getById(id);
        if (request.nome() != null) dipendente.setNome(request.nome());
        if(request.cognome() != null) dipendente.setCognome(request.cognome());
        if (request.dataNascita() != null) dipendente.setDataNascita(request.dataNascita());
        if(request.luogo_nascita_id() != null) dipendente.setLuogoNascita(comuneService.getById(request.luogo_nascita_id().id()));
        if (request.email() != null) dipendente.setEmail(request.email());
        if(request.password() != null) dipendente.setPassword(request.password());
        if (request.telefono() != null) dipendente.setTelefono(request.telefono());
        if(request.immagineProfilo() != null) dipendente.setImmagineProfilo(request.immagineProfilo());
        if(request.posizione_lavorativa_id() != null) dipendente.setPosizioneLavorativa(posizioneLavorativaService.getById(request.posizione_lavorativa_id().id()));

        return new EntityIdResponse(dipendenteRepository.save(dipendente).getId());
    }

    public DipendenteResponse getProfilo(Long id) throws MyEntityNotFoundException{
        Dipendente dipendente = getById(id);
        return dipendenteMapper.profiloDipendente(dipendente);
    }

    public Dipendente getByEmail(String email) throws MyEntityNotFoundException {
        return dipendenteRepository
                .findByEmail(email)
                .orElseThrow(() -> new MyEntityNotFoundException("Dipendente con email " + email + " non trovato"));
    }

    public String getNome(Long id){
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        return dipendente.map(Dipendente::getNome).orElse(null);
    }

    public Long getId(Long id){
        Optional<Dipendente> dipendente = dipendenteRepository.findById(id);
        return dipendente.map(Dipendente::getId).orElse(null);
    }

    public Dipendente getByRegistrationToken(String token) {
        return dipendenteRepository
                .findByRegistrationToken(token)
                .orElseThrow(() -> new MyEntityNotFoundException("Dipendente con token " + token + " non trovato"));
    }


}
