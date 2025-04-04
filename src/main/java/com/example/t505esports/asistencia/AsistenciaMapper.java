package com.example.t505esports.asistencia;

import com.example.t505esports.evento.Evento;
import com.example.t505esports.jugador.Jugador;

public class AsistenciaMapper {

    public static AsistenciaDTO toDTO(Asistencia a) {
        return new AsistenciaDTO(
                a.getId(),
                a.getEvento().getId(),
                a.getJugador().getId(),
                a.getFechaConfirmacion()
        );
    }

    public static Asistencia toEntity(AsistenciaDTO dto, Evento evento, Jugador jugador) {
        return Asistencia.builder()
                .id(dto.id())
                .evento(evento)
                .jugador(jugador)
                .fechaConfirmacion(dto.fechaConfirmacion())
                .build();
    }
}

