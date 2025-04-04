package com.example.t505esports.jugador;

import java.util.List;
import java.util.UUID;

public interface JugadorService {
    JugadorDTO crearJugador(JugadorDTO jugadorDTO);
    JugadorDTO editarJugador(JugadorDTO jugadorDTO);
    List<JugadorDTO> obtenerTodos();
    JugadorDTO obtenerPorId(UUID id);
    void eliminarPorId(UUID id);

}

