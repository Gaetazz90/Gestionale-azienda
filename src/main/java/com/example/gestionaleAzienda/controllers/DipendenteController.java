package com.example.gestionaleAzienda.controllers;


import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.DipendenteMapper;
import com.example.gestionaleAzienda.services.DipendenteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/dipendente")
@Validated
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Dipendente> getById(@PathVariable Long id) {
        return new ResponseEntity<>(dipendenteService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Dipendente>> getAll() {
        return new ResponseEntity<>(dipendenteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/profilo/{id}")
    public ResponseEntity<DipendenteResponse> getProfilo(@PathVariable Long id) {
        return new ResponseEntity<>(dipendenteService.getProfilo(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insert(@RequestBody @Valid DipendenteRequest request) {
        return new ResponseEntity<>(dipendenteService.creaDipendente(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateDipendenteRequest request) {
        return new ResponseEntity<>(dipendenteService.updateDipendente(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        dipendenteService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("Dipendente con id " + id + " eliminato correttamente"), HttpStatus.OK);
    }

}
