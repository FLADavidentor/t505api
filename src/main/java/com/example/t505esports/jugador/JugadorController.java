package com.example.t505esports.jugador;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/player/jugadores")
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorService jugadorService;

    @PostMapping
    public ResponseEntity<JugadorDTO> crearJugador(@RequestBody JugadorDTO dto) {
        return ResponseEntity.ok(jugadorService.crearJugador(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JugadorDTO> editarJugador(@PathVariable UUID id, @RequestBody JugadorDTO dto) {
        JugadorDTO dtoConId = new JugadorDTO(id, dto.nombre(), dto.rol());
        return ResponseEntity.ok(jugadorService.editarJugador(dtoConId));
    }


    @GetMapping
    public ResponseEntity<List<JugadorDTO>> obtenerTodos() {
        return ResponseEntity.ok(jugadorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(jugadorService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable UUID id) {
        jugadorService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}

