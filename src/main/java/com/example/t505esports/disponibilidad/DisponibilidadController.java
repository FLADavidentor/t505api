package com.example.t505esports.disponibilidad;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;

    @PostMapping
    public ResponseEntity<Void> crearDisponibilidades(@RequestBody List<DisponibilidadDTO> dtoList) {
        disponibilidadService.guardarMultiples(dtoList);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<DisponibilidadDTO>> listar() {
        List<DisponibilidadDTO> lista = disponibilidadService.listar();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<DisponibilidadDTO>> listarPorJugador(@PathVariable String jugadorId) {
        List<DisponibilidadDTO> lista = disponibilidadService.listarPorJugador(jugadorId);
        return ResponseEntity.ok(lista);
    }

}
