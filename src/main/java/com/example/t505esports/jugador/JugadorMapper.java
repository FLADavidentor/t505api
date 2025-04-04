package com.example.t505esports.jugador;


public class JugadorMapper {

    public static JugadorDTO toDTO(Jugador jugador) {
        return new JugadorDTO(
                jugador.getId(),
                jugador.getNombre(),
                jugador.getRol()
        );
    }

    public static Jugador toEntity(JugadorDTO dto) {
        return Jugador.builder()
                .id(dto.id())
                .nombre(dto.nombre())
                .rol(dto.rol())
                .build();
    }
}
