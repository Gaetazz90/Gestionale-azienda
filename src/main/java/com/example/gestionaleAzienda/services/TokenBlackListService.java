package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.entities.TokenBlackList;
import com.example.gestionaleAzienda.repositories.TokenBlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService {

    @Autowired
    private TokenBlackListRepository tokenBlackListRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Boolean isPresentToken(String token) {
        return tokenBlackListRepository.getByToken(token).isPresent();
    }

    public void insertToken(Long id_dipendente, String token) {
        TokenBlackList tokenBlackList = TokenBlackList
                .builder()
                .token(token)
                .dipendente(dipendenteService.getById(id_dipendente))
                .build();
        tokenBlackListRepository.save(tokenBlackList);
    }

}
