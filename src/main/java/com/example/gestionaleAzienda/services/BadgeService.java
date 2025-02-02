package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.BadgeRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateBadgeRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.BadgeResponse;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Badge;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.exceptions.BadgeAlreadyAssignedException;
import com.example.gestionaleAzienda.domain.exceptions.IllegalTimeException;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.BadgeMapper;
import com.example.gestionaleAzienda.repositories.BadgeRepository;
import com.example.gestionaleAzienda.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private BadgeMapper badgeMapper;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Badge getById(Long id) {
        return badgeRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException("Badge con id " + id + " non trovato"));
    }

    public List<Badge> getAll(){
        return badgeRepository.findAll();
    }

    public void deleteById(Long id) {
        badgeRepository.deleteById(id);
    }

    public EntityIdResponse assegna(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id).orElseThrow();

        Badge badge = Badge.builder().dipendente(dipendente).orarioInizio(LocalDateTime.now()).build();

        List<Badge> lista = badgeRepository.findByDipendente(dipendente);
        if(lista.stream().anyMatch(b -> b.getOrarioInizio().toLocalDate().isEqual(badge.getOrarioInizio().toLocalDate()))){
            throw new BadgeAlreadyAssignedException("Badge già timbrato TRMOOOON!");
        }

        badgeRepository.save(badge);

        return new EntityIdResponse(badge.getId());
    }

    public BadgeResponse getBadge(Long id) {
        Badge badge = getById(id);
        return badgeMapper.badge(badge);
    }

    public EntityIdResponse timbraturaInizioPausa(Long id){
        Badge badge = getById(id);
        if (badge.getInizioPausa() != null){
            throw new IllegalTimeException("Orario illegale");
        }
        badge.setInizioPausa(LocalDateTime.now());
        return new EntityIdResponse(badgeRepository.save(badge).getId());
    }

    public EntityIdResponse timbraturaFinePausa(Long id){
        Badge badge = getById(id);
        if (badge.getFinePausa() != null || badge.getInizioPausa() == null) {
            throw new IllegalTimeException("Stai fuso");
        }

        badge.setFinePausa(LocalDateTime.now());
        return new EntityIdResponse(badgeRepository.save(badge).getId());
    }

    public EntityIdResponse timbraturaFineGiornata(Long id){
        Badge badge = getById(id);
        if (badge.getOrarioFine() != null || badge.getFinePausa() == null){
            throw new IllegalTimeException("Gaetano stai calmo");
        }
        badge.setOrarioFine(LocalDateTime.now());
        return new EntityIdResponse(badgeRepository.save(badge).getId());
    }
}
