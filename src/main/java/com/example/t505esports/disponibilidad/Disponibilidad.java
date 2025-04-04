package com.example.t505esports.disponibilidad;

import com.example.t505esports.jugador.Jugador;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Disponibilidad {

    @Id
    @GeneratedValue
    private UUID id;

    private DayOfWeek dia;         // LUNES, MARTES, etc.
    private LocalTime horaInicio;  // 19:00
    private LocalTime horaFin;     // 22:00

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
}

