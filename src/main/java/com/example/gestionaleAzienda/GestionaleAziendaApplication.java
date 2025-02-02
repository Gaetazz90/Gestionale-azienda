package com.example.gestionaleAzienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionaleAziendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionaleAziendaApplication.class, args);
	}

}
