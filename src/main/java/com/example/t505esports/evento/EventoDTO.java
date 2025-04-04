package com.example.t505esports.evento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EventoDTO(
        UUID id,
        String titulo,
        TipoEvento tipo,
        LocalDateTime inicio,
        LocalDateTime fin,
        List<UUID> jugadores
) {}
