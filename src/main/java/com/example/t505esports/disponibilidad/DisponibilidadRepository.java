package com.example.t505esports.disponibilidad;

import com.example.t505esports.jugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, UUID> {
    List<Disponibilidad> findByJugador(Jugador jugador);
    List<Disponibilidad> findByDia(DayOfWeek dia);
    List<Disponibilidad> deleteByJugadorId(UUID JugadorId);
}
