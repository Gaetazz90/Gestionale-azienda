package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Badge;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    List<Badge> findByDipendente(Dipendente dipendente);
}
