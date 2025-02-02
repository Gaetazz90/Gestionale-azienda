package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.PosizioneLavorativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosizioneLavorativaRepository extends JpaRepository<PosizioneLavorativa, Long> {
}
