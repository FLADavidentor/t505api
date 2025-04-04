package com.example.t505esports.disponibilidad;

import com.example.t505esports.jugador.Jugador;

public class DisponibilidadMapper {

    public static DisponibilidadDTO toDTO(Disponibilidad d) {
        return new DisponibilidadDTO(
                d.getId(),
                d.getDia(),
                d.getHoraInicio(),
                d.getHoraFin(),
                d.getJugador().getId()
        );
    }

    public static Disponibilidad toEntity(DisponibilidadDTO dto, Jugador jugador) {
        return Disponibilidad.builder()
                .id(dto.id())
                .dia(dto.dia())
                .horaInicio(dto.horaInicio())
                .horaFin(dto.horaFin())
                .jugador(jugador)
                .build();
    }
}