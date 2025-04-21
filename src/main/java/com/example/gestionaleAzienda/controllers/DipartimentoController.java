package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.DipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipartimentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.response.DipendenteResponse;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Dipartimento;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.services.DipartimentoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/dipartimento")
public class DipartimentoController {

    @Autowired
    private DipartimentoService dipartimentoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Dipartimento> getById(@PathVariable Long id) {
        return new ResponseEntity<>(dipartimentoService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Dipartimento>> getAll() {
        return new ResponseEntity<>(dipartimentoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insert(@RequestBody @Valid DipartimentoRequest request){
        return new ResponseEntity<>(dipartimentoService.creaDipartimento(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody UpdateDipartimentoRequest request) {
        return new ResponseEntity<>(dipartimentoService.updateDipartimento(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        dipartimentoService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("Dipartimento con id " + id + " eliminato correttamente"), HttpStatus.OK);
    }
}
