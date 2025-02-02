package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.CommentoRequest;
import com.example.gestionaleAzienda.domain.dto.request.create.NewsRequest;
import com.example.gestionaleAzienda.domain.dto.response.NewsResponse;
import com.example.gestionaleAzienda.domain.entities.Commento;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.entities.News;
import com.example.gestionaleAzienda.services.DipendenteService;
import com.example.gestionaleAzienda.services.NewsService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class CommentoMapper {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private NewsService newsService;

    public Commento fromRequestToComment(CommentoRequest request){
        Dipendente dipendente = dipendenteService.getById(request.utente_id().id());
        News news = newsService.getById(request.news_id().id());
        return Commento.builder()
                .contenuto(request.contenuto())
                .dipendente(dipendente)
                .news(news)
                .build();
    }


}
