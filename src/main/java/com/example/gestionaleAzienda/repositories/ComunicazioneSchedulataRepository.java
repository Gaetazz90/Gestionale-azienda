package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import com.example.gestionaleAzienda.domain.entities.ComunicazioneSchedulata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComunicazioneSchedulataRepository extends JpaRepository<ComunicazioneSchedulata, Long> {

    @Query(value = "SELECT c FROM Comunicazione c WHERE c.dipendente.id = :dipendenteId", nativeQuery = true)
    List<ComunicazioneSchedulata> findAllByIdDipendente(@Param("dipendenteId") Long dipendenteId);
}
