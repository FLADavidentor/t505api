package com.example.t505esports.jugador;

import com.example.t505esports.evento.Evento;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jugador {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private RolJugador rol;


    @ManyToMany(mappedBy = "jugadores")
    private Set<Evento> eventos;
}

