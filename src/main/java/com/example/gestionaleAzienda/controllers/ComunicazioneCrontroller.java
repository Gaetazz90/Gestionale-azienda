package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateComunicazioneRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Comunicazione;
import com.example.gestionaleAzienda.domain.entities.ComunicazioneSchedulata;
import com.example.gestionaleAzienda.services.ComunicazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/comunicazione")
@Validated
public class ComunicazioneCrontroller {

    @Autowired
    private ComunicazioneService comunicazioneService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Comunicazione> getById(@PathVariable Long id){
        return new ResponseEntity<>(comunicazioneService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Comunicazione>> getAll() {
        return new ResponseEntity<>(comunicazioneService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/id_utente/{id}")
    public ResponseEntity<List<Comunicazione>> getAllByIdUtente(@PathVariable Long id) {
        return new ResponseEntity<>(comunicazioneService.getAllByIdUtente(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insert(@RequestBody @Valid ComunicazioneRequest request){
        return new ResponseEntity<>(comunicazioneService.creaComunicazione(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody UpdateComunicazioneRequest request) {
        return new ResponseEntity<>(comunicazioneService.updateComunicazione(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        comunicazioneService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("Comunicazione con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }

}
