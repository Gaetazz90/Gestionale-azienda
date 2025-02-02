package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.BadgeRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateBadgeRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.BadgeResponse;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Badge;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.services.BadgeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/badge")
@Validated
public class BadgeController {

    @Autowired
    private BadgeService badgeService;


    @GetMapping
    public ResponseEntity<Badge> getById(@PathVariable Long id){
        return new ResponseEntity<>(badgeService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Badge>> getAll() {
        return new ResponseEntity<>(badgeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/badge/{id}")
    public ResponseEntity<BadgeResponse> getBadgeDipendente(@PathVariable Long id) {
        return new ResponseEntity<>(badgeService.getBadge(id), HttpStatus.OK);
    }

    @PostMapping("/insert/{idDipendente}")
    public ResponseEntity<EntityIdResponse> insert(@PathVariable @Valid Long idDipendente) {
        return new ResponseEntity<>(badgeService.assegna(idDipendente), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        badgeService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("Badge con id " + id + " eliminato correttamente"), HttpStatus.OK);
    }

    @PutMapping("/inizioPausa/{id}")
    public ResponseEntity<EntityIdResponse> inizioPausa(@PathVariable Long id) {
        return new ResponseEntity<>(badgeService.timbraturaInizioPausa(id), HttpStatus.CREATED);
    }

    @PutMapping("/finePausa/{id}")
    public ResponseEntity<EntityIdResponse> finePausa(@PathVariable Long id) {
        return new ResponseEntity<>(badgeService.timbraturaFinePausa(id), HttpStatus.CREATED);
    }

    @PutMapping("/fineGiornata/{id}")
    public ResponseEntity<EntityIdResponse> fineGiornata(@PathVariable Long id) {
        return new ResponseEntity<>(badgeService.timbraturaFineGiornata(id), HttpStatus.CREATED);
    }

}
