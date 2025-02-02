package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentoRepository extends JpaRepository<Commento, Long> {
}
