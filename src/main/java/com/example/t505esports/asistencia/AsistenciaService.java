package com.example.t505esports.asistencia;

import java.util.List;
import java.util.UUID;

public interface AsistenciaService {
    AsistenciaDTO registrarAsistencia(UUID eventoId, UUID jugadorId);
    List<AsistenciaDTO> listarPorEvento(UUID eventoId);
    List<AsistenciaDTO> listarPorJugador(UUID jugadorId);
}