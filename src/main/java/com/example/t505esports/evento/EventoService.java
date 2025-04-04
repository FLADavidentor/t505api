package com.example.t505esports.evento;

import java.util.List;

public interface EventoService {
    EventoDTO crearEvento(EventoDTO dto);
    List<EventoDTO> listarEventos();
}