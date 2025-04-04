package com.example.t505esports.evento;

import com.example.t505esports.jugador.Jugador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue
    private UUID id;

    private String titulo;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    @ManyToMany
    @JoinTable(
            name = "evento_jugadores",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "jugador_id")
    )
    private Set<Jugador> jugadores = new HashSet<>();
}
