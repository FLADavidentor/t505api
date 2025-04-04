package com.example.t505esports.reporte;

import com.example.t505esports.jugador.RolJugador;

import java.util.UUID;

public record ReporteAsistenciaDTO(
        UUID jugadorId,
        String nombreJugador,
        RolJugador rol,
        int eventosAsignados,
        int eventosAsistidos,
        double porcentajeAsistencia
) {}
