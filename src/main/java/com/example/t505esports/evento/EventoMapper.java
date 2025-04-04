package com.example.t505esports.evento;

import com.example.t505esports.jugador.Jugador;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class EventoMapper {

    public static EventoDTO toDTO(Evento e) {
        List<UUID> jugadorIds = e.getJugadores().stream()
                .map(Jugador::getId)
                .toList();

        return new EventoDTO(
                e.getId(),
                e.getTitulo(),
                e.getTipo(),
                e.getInicio(),
                e.getFin(),
                jugadorIds
        );
    }

    public static Evento toEntity(EventoDTO dto, Set<Jugador> jugadores) {

        return Evento.builder()
                .id(dto.id())
                .titulo(dto.titulo())
                .tipo(dto.tipo())
                .inicio(dto.inicio())
                .fin(dto.fin())
                .jugadores(jugadores)
                .build();
    }
}
