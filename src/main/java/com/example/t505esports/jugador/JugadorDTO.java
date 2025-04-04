package com.example.t505esports.jugador;

import java.util.UUID;

public record JugadorDTO(
        UUID id,
        String nombre,
        RolJugador rol
) {}

