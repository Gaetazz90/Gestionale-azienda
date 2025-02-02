package com.example.gestionaleAzienda.services;

import com.example.gestionaleAzienda.domain.dto.request.create.NewsRequest;
import com.example.gestionaleAzienda.domain.dto.request.update.UpdateNewsRequest;
import com.example.gestionaleAzienda.domain.dto.response.EntityIdResponse;
import com.example.gestionaleAzienda.domain.dto.response.NewsResponse;
import com.example.gestionaleAzienda.domain.entities.News;
import com.example.gestionaleAzienda.domain.exceptions.MyEntityNotFoundException;
import com.example.gestionaleAzienda.mappers.NewsMapper;
import com.example.gestionaleAzienda.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    public News getById(Long id){
        return  newsRepository.findById(id).orElseThrow(()-> new MyEntityNotFoundException("News con id: " + id + " non trovata"));
    }

    public List<NewsResponse> getAll(){
        return newsRepository.findAll().stream().map(newsMapper::fromNewsToResponse).toList();
    }

    public List<NewsResponse> getAllByIdDipendente(Long idDipendente){
        return newsRepository.findAll().stream()
                                        .filter(news -> news.getDipendente().getId().equals(idDipendente))
                                        .map(newsMapper::fromNewsToResponse)
                                        .toList();
    }

    public EntityIdResponse createNews(NewsRequest request){
        News news = newsRepository.save(newsMapper.fromRequestToNews(request));
        return new EntityIdResponse(news.getId());
    }

    public EntityIdResponse updateNews(Long id, UpdateNewsRequest request){
        News news = getById(id);
        if (request.titolo() != null) news.setTitolo(request.titolo());
        if (request.contenuto() != null) news.setContenuto(request.contenuto());
        if (request.immagine() != null)news.setImmagine(request.immagine());
        return new EntityIdResponse(newsRepository.save(news).getId());
    }

    public void deleteById(Long id){
        newsRepository.deleteById(id);
    }


}
