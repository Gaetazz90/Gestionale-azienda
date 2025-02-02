package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.DipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.PosizioneLavorativaRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateDipendenteRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdatePosizioneLavorativaRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.dto.response.PosizioneLavorativaResponse;
import com.example.gestionaleAzienda.domain.entities.PosizioneLavorativa;
import com.example.gestionaleAzienda.services.PosizioneLavorativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/posizione")
public class PosizioneLavorativaController {

    @Autowired
    private PosizioneLavorativaService posizioneLavorativaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PosizioneLavorativa> getById(@PathVariable Long id){
        return new ResponseEntity<>(posizioneLavorativaService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/pos/{id}")
    public ResponseEntity<PosizioneLavorativaResponse> getPosizione(@PathVariable Long id){
        return new ResponseEntity<>(posizioneLavorativaService.getPosizione(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PosizioneLavorativa>> getAll(){
        return new ResponseEntity<>(posizioneLavorativaService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insert(@RequestBody @Valid PosizioneLavorativaRequest request) {
        return new ResponseEntity<>(posizioneLavorativaService.creaPosizione(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> update(@PathVariable Long id, @RequestBody @Valid UpdatePosizioneLavorativaRequest request) {
        return new ResponseEntity<>(posizioneLavorativaService.updatePosizione(id, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteById(@PathVariable Long id) {
        posizioneLavorativaService.deleteById(id);
        return new ResponseEntity<>(
                new GenericResponse("Posizione lavorativa con id " + id + " eliminata correttamente"), HttpStatus.OK);
    }



}
