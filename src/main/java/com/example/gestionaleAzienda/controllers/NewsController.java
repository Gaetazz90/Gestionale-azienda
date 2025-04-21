package com.example.gestionaleAzienda.controllers;

import com.example.gestionaleAzienda.domain.dto.request.create.CommentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.LikeRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.NewsRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateCommentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateNewsRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.dto.response.NewsResponse;
import com.example.gestionaleAzienda.domain.entities.Commento;
import com.example.gestionaleAzienda.domain.entities.MiPiace;
import com.example.gestionaleAzienda.domain.entities.News;
import com.example.gestionaleAzienda.domain.exceptions.NotMatchException;
import com.example.gestionaleAzienda.repositories.NewsRepository;
import com.example.gestionaleAzienda.services.CommentoService;
import com.example.gestionaleAzienda.services.MiPiaceService;
import com.example.gestionaleAzienda.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MiPiaceService miPiaceService;

    @Autowired
    private CommentoService commentoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id){
        return new ResponseEntity<>( newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity <List<NewsResponse>> getAll(){
        return ResponseEntity.ok( newsService.getAll());
    }

    @PostMapping("/insert")
    public ResponseEntity<EntityIdResponse> insertNews(@RequestBody NewsRequest request){
        return new ResponseEntity<>(newsService.createNews(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EntityIdResponse> updateNews(@PathVariable Long id, @RequestBody UpdateNewsRequest request){
        return new ResponseEntity<>(newsService.updateNews(id,request), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteNewsById(@PathVariable Long id){
        newsService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("News con id: " + id + " eliminata con successo"), HttpStatus.OK);
    }

    @PostMapping("/{idNews}/dipendente/{idDipendente}/insert/like")
    public ResponseEntity<EntityIdResponse> insertLike(@RequestBody LikeRequest request){
        return new ResponseEntity<>(miPiaceService.mittULike(request), HttpStatus.CREATED);
    }

    @GetMapping("/getLike/all/{idNews}")
    public ResponseEntity <List<MiPiace>> getAllLikes(@PathVariable Long idNews){
        return ResponseEntity.ok( miPiaceService.getAllByIdNews(idNews));
    }

    @DeleteMapping("/{idNews}/dipendente/{idDipendente}/delete/like/{idLike}")
    public ResponseEntity<GenericResponse> deleteLikeById(@PathVariable Long idNews, @PathVariable Long idLike, @PathVariable Long idDipendente){
        miPiaceService.deleteById(idNews, idLike, idDipendente);
        return new ResponseEntity<>(new GenericResponse("Like con id: " + idLike + " eliminato con successo"), HttpStatus.OK);
    }

    @GetMapping("/getCommenti/{idNews}")
    public ResponseEntity<List<Commento>> getCommentiByIdNews(@PathVariable Long idNews){
        return ResponseEntity.ok(commentoService.getAllByIdNews(idNews));
    }

    @PostMapping("/insert_commento")
    public ResponseEntity<EntityIdResponse> insertCommento(@RequestBody CommentoRequest request){
        return new ResponseEntity<>(commentoService.createCommento(request), HttpStatus.CREATED);
    }

    @PutMapping("/{idNews}/dipendente/{idDipendente}/updateCommento/{idCommento}")
    public ResponseEntity<EntityIdResponse> updateCommento(@PathVariable Long idNews, @PathVariable Long idDipendente, @PathVariable Long idCommento,@RequestBody UpdateCommentoRequest request){
        return new ResponseEntity<>(commentoService.updateCommento(idNews, idDipendente, idCommento, request), HttpStatus.CREATED);
    }


    @DeleteMapping("/delete_commento/{id}")
    public ResponseEntity<GenericResponse> deleteCommentoById(@PathVariable Long idCommento){
        commentoService.deleteById(idCommento);
        return new ResponseEntity<>(new GenericResponse("Commento con id: " + idCommento + " eliminato con successo!"), HttpStatus.OK);
    }


}
