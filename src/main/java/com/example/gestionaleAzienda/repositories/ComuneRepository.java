package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long> {
}
