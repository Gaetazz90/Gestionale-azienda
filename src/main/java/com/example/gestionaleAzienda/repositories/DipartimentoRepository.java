package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DipartimentoRepository extends JpaRepository<Dipartimento, Long> {

}
