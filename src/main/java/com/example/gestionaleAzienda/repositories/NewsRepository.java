package com.example.gestionaleAzienda.repositories;

import com.example.gestionaleAzienda.domain.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
