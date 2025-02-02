package com.example.gestionaleAzienda.mappers;

import com.example.gestionaleAzienda.domain.dto.request.create.NewsRequest;
import com.example.gestionaleAzienda.domain.dto.response.NewsResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.entities.News;
import com.example.gestionaleAzienda.services.DipendenteService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class NewsMapper {

    @Autowired
    private DipendenteService dipendenteService;

    public News fromRequestToNews(NewsRequest request){
        Dipendente dipendente = dipendenteService.getById(request.idDipendente());
        return News.builder()
                .titolo(request.titolo())
                .contenuto(request.contenuto())
                .dipendente(dipendente)
                .build();
    }

    public NewsResponse fromNewsToResponse(News news){
        return NewsResponse.builder()
                .id(news.getId())
                .titolo(news.getTitolo())
                .contenuto(news.getContenuto())
                .immagine(news.getImmagine())
                .idDipendente(news.getDipendente().getId())
                .build();
    }

}
