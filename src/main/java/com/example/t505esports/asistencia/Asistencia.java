package com.example.t505esports.asistencia;

import com.example.t505esports.evento.Evento;
import com.example.t505esports.jugador.Jugador;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    private LocalDateTime fechaConfirmacion;
}

