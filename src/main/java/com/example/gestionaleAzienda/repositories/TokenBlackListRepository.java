package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenBlackListRepository extends JpaRepository<TokenBlackList,Long> {

    Optional<TokenBlackList> getByToken(String token);

}
