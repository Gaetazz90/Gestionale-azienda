package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicazioneRepository extends JpaRepository<Comunicazione, Long> {

    @Query(value = "SELECT c FROM Comunicazione c WHERE c.dipendente.id = :dipendenteId", nativeQuery = true)
    List<Comunicazione> findAllByIdDipendente(@Param("dipendenteId") Long dipendenteId);
}
