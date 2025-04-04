package com.example.t505esports.evento;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/eventos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EventoController {

    private final EventoService service;

    @PostMapping
    public ResponseEntity<EventoDTO> crear(@RequestBody EventoDTO dto) {
        return ResponseEntity.ok(service.crearEvento(dto));
    }

    @GetMapping
    public ResponseEntity<List<EventoDTO>> listar() {
        return ResponseEntity.ok(service.listarEventos());
    }
}
