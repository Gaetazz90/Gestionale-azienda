package com.example.gestionaleAzienda.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orario_inizio", nullable = false, updatable = false)
    private LocalDateTime orarioInizio;

    @Column(name = "orario_fine")
    private LocalDateTime orarioFine;

    @Column(name = "inizio_pausa")
    private LocalDateTime inizioPausa;

    @Column(name = "fine_pausa")
    private LocalDateTime finePausa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dipendente")
    private Dipendente dipendente;

}
