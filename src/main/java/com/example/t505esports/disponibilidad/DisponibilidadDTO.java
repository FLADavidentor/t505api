package com.example.t505esports.disponibilidad;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

public record DisponibilidadDTO(
        UUID id,
        DayOfWeek dia,
        LocalTime horaInicio,
        LocalTime horaFin,
        UUID jugadorId
) {}
