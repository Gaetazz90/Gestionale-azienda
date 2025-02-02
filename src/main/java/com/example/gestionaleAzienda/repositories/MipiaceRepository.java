package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.MiPiace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MipiaceRepository extends JpaRepository<MiPiace, Long> {
}
