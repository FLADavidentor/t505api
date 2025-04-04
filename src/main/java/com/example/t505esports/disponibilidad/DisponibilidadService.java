package com.example.t505esports.disponibilidad;

import com.example.t505esports.jugador.JugadorDTO;
import com.example.t505esports.jugador.RolJugador;

import java.util.List;
import java.util.UUID;

public interface DisponibilidadService {
    DisponibilidadDTO crear(DisponibilidadDTO dto);
    List<DisponibilidadDTO> listar();
    List<DisponibilidadDTO> listarPorJugador(String jugadorId);

    void guardarMultiples(List<DisponibilidadDTO> lista);

}

