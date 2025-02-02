package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.entities.Comune;
import com.example.gestionaleAzienda.repositories.ComuneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public Comune getById(Long id) throws EntityNotFoundException {
        return comuneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comune non trovato"));
    }

}
