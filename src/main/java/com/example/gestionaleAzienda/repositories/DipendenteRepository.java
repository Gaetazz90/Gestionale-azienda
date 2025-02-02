package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByRegistrationToken(String token);
}
