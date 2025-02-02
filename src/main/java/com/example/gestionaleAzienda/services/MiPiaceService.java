package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.LikeRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.entities.Dipendente;
import com.example.gestionaleAzienda.domain.entities.MiPiace;
import com.example.gestionaleAzienda.domain.entities.News;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.domain.exceptions.NotLikeException;
import com.example.gestionaleAzienda.domain.exceptions.NotMatchException;
import com.example.gestionaleAzienda.repositories.DipendenteRepository;
import com.example.gestionaleAzienda.repositories.MipiaceRepository;
import com.example.gestionaleAzienda.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiPiaceService {

    @Autowired
    private MipiaceRepository mipiaceRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private NewsService newsService;

    public List<MiPiace> getAllByIdNews(Long idNews){
        return mipiaceRepository.findAll().stream().filter(miPiace -> miPiace.getNews().getId().equals(idNews)).toList();
    }

    public List<MiPiace> getAllByIdNewsEIdUtente(Long idNews, Long idUtente){
        return mipiaceRepository.findAll().stream().filter(miPiace -> miPiace.getNews().getId().equals(idNews) && miPiace.getDipendente().getId().equals(idUtente)).toList();
    }

    public EntityIdResponse mittULike(LikeRequest request){
        List<MiPiace> likesTotali = getAllByIdNewsEIdUtente(request.news_id(), request.dipendente_id());
        if(!likesTotali.isEmpty()){
            throw new NotLikeException("Non puoi mettere di nuovo like alla news con id: " + request.news_id());
        }
        Dipendente dipendente = dipendenteService.getById(request.dipendente_id());
        News news = newsService.getById(request.news_id());
        MiPiace miPiace = MiPiace.builder()
                .dipendente(dipendente)
                .news(news)
                .build();
        MiPiace newMiPiace = mipiaceRepository.save(miPiace);
        return new EntityIdResponse(newMiPiace.getId());
    }

    public void deleteById(Long idNews, Long idLike, Long idDipendente){
        News news = newsService.getById(idNews);
        List<MiPiace> likesNews = getAllByIdNewsEIdUtente(idNews, idDipendente).stream().filter(miPiace -> miPiace.getId().equals(idLike)).toList();

        if(likesNews.isEmpty()){
            throw new NotMatchException("Non puoi cancellare il like di una altro utente!");
        }
        mipiaceRepository.deleteById(idLike);
    }

}
