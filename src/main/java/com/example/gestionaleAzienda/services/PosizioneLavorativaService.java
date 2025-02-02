package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.PosizioneLavorativaRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdatePosizioneLavorativaRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.PosizioneLavorativaResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.entities.PosizioneLavorativa;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.PosizioneLavorativaMapper;
import com.example.gestionaleAzienda.repositories.PosizioneLavorativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosizioneLavorativaService {

    @Autowired
    private PosizioneLavorativaRepository posizioneLavorativaRepository;
    @Autowired
    private PosizioneLavorativaMapper posizioneLavorativaMapper;

    public PosizioneLavorativa getById(Long id){
        return posizioneLavorativaRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Posizione lavorativa con id " + id + " non trovata"));
    }

    public PosizioneLavorativaResponse getPosizione(Long id) {
        PosizioneLavorativa posizione = getById(id);
        return posizioneLavorativaMapper.getPosizione(posizione);
    }

    public List<PosizioneLavorativa> getAll(){
        return posizioneLavorativaRepository.findAll();
    }

    public void deleteById(Long id) {
        posizioneLavorativaRepository.deleteById(id);
    }

    public EntityIdResponse creaPosizione(PosizioneLavorativaRequest request){
        PosizioneLavorativa pos = posizioneLavorativaRepository.save(posizioneLavorativaMapper.fromPosizioneLavorativaRequest(request));
        return new EntityIdResponse(pos.getId());
    }

    public EntityIdResponse updatePosizione(Long id, UpdatePosizioneLavorativaRequest request){
        PosizioneLavorativa pos = getById(id);
        if (request.nome() != null) pos.setNome(request.nome());
        if(request.descrizione() != null) pos.setDescrizione(request.descrizione());

        return new EntityIdResponse(posizioneLavorativaRepository.save(pos).getId());
    }









}
