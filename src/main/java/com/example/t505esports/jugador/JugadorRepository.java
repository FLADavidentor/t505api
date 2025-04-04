package com.example.t505esports.jugador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JugadorRepository extends JpaRepository<Jugador, UUID> {
}

