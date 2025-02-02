package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.ComunicazioneSchedulataRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateComunicazioneSchedulataRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.ComunicazioneSchedulata;
import com.example.gestionaleAzienda.services.ComunicazioneSchedulataService;
import jakarta.validation.Valid;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/comunicazione_schedulata")
@Validated
public class ComunicazioneSchedulataController {
    @Autowired
    private ComunicazioneSchedulataService comunicazioneSchedulataService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ComunicazioneSchedulata> getById(@PathVariable Long id){
        return new ResponseEntity<>(comunicazioneSchedulataService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ComunicazioneSchedulata>> getAll() {
        return new ResponseEntity<>(comunicazioneSchedulataService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all/id_utente/{id}")
    public ResponseEntity<List<ComunicazioneSchedulata>> getAllByIdUtente(@PathVariable Long id) {
        return new ResponseEntity<>(comunicazioneSchedulataService.getAllByIdUtente(id), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insert(@RequestBody @Valid ComunicazioneSchedulataRequest request) throws SchedulerException {
        return new ResponseEntity<>(comunicazioneSchedulataService.creaComunicazioneSchedulata(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody UpdateComunicazioneSchedulataRequest request) throws SchedulerException {
        return new ResponseEntity<>(comunicazioneSchedulataService.updateComunicazioneSchedulata(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) throws SchedulerException {
        comunicazioneSchedulataService.deleteComunicazioneSchedulataById(id);
        return new ResponseEntity<>(
                new GenericResponse("Comunicazione schedulata con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }
}
