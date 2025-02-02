package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.CommentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateCommentoRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.GenericResponse;
import com.example.gestionaleAzienda.domain.entities.Commento;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.domain.exceptions.NotMatchException;
import com.example.gestionaleAzienda.mappers.CommentoMapper;
import com.example.gestionaleAzienda.repositories.CommentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentoService {

    @Autowired
    private CommentoRepository commentoRepository;
    @Autowired
    private CommentoMapper commentoMapper;

    public Commento getById(Long id){
        return commentoRepository.findById(id).orElseThrow(()-> new MyEntityNotFoundException("Commento con id: " + id +  " non trovato"));
    }

    public List<Commento> getAll(){
        return commentoRepository.findAll();
    }

    public List<Commento> getAllByIdNews(Long idNews){
        return commentoRepository.findAll().stream().filter(commento -> commento.getNews().getId().equals(idNews)).toList();
    }

    public EntityIdResponse createCommento(CommentoRequest request){
        Commento commento = commentoRepository.save(commentoMapper.fromRequestToComment(request));
        return new EntityIdResponse(commento.getId());
    }

    public EntityIdResponse updateCommento(Long idNews, Long idDipendente, Long idCommento, UpdateCommentoRequest request){
        Commento commento = getById(idCommento);
        if(!commento.getNews().getId().equals(idNews) && !commento.getDipendente().getId().equals(idDipendente)){
            throw new NotMatchException("Non puoi modificare il commento di un altro utente o di un altra news");
        }
        if(commento.getContenuto() != null)commento.setContenuto(request.contenuto());
        return new EntityIdResponse(commentoRepository.save(commento).getId());
    }

    public void deleteById(Long id){
        commentoRepository.deleteById(id);
    }

}
