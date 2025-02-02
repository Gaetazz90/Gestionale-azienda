package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.DipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.mappers.DipartimentoMapper;
import com.example.gestionaleAzienda.repositories.DipartimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipartimentoService {

    @Autowired
    private DipartimentoRepository dipartimentoRepository;
    @Autowired
    private DipartimentoMapper dipartimentoMapper;

    public Dipartimento getById(Long id){
        return dipartimentoRepository.findById(id).orElseThrow();
    }

    public List<Dipartimento> getAll(){
        return dipartimentoRepository.findAll();
    }

    public void deleteById(Long id) {
        dipartimentoRepository.deleteById(id);
    }

    public EntityIdResponse creaDipartimento(DipartimentoRequest request){
        Dipartimento dip = dipartimentoRepository.save(dipartimentoMapper.fromDipartimentoRequest(request));
        return new EntityIdResponse(dip.getId());
    }

    public EntityIdResponse updateDipartimento(Long id, UpdateDipartimentoRequest request){
        Dipartimento dip = getById(id);
        if (request.nome() != null) dip.setNome(request.nome());
        if(request.descrizione() != null) dip.setDescrizione(request.descrizione());

        return new EntityIdResponse(dipartimentoRepository.save(dip).getId());
    }




}
