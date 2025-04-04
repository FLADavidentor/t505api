package com.example.t505esports.asistencia;

import java.time.LocalDateTime;
import java.util.UUID;

public record AsistenciaDTO(
        UUID id,
        UUID eventoId,
        UUID jugadorId,
        LocalDateTime fechaConfirmacion
) {}

