package com.example.t505esports.asistencia;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player/asistencia")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService service;

    @PostMapping("/{eventoId}/{jugadorId}")
    public ResponseEntity<AsistenciaDTO> registrar(
            @PathVariable UUID eventoId,
            @PathVariable UUID jugadorId
    ) {
        return ResponseEntity.ok(service.registrarAsistencia(eventoId, jugadorId));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<AsistenciaDTO>> porEvento(@PathVariable UUID eventoId) {
        return ResponseEntity.ok(service.listarPorEvento(eventoId));
    }

    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<AsistenciaDTO>> porJugador(@PathVariable UUID jugadorId) {
        return ResponseEntity.ok(service.listarPorJugador(jugadorId));
    }
}
