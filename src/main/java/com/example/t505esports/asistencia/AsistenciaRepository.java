package com.example.t505esports.asistencia;

import com.example.t505esports.evento.Evento;
import com.example.t505esports.jugador.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AsistenciaRepository extends JpaRepository<Asistencia, UUID> {
    List<Asistencia> findByEvento(Evento evento);
    List<Asistencia> findByJugador(Jugador jugador);
    Optional<Asistencia> findByEventoAndJugador(Evento evento, Jugador jugador);
}
