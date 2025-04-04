package com.example.t505esports.asistencia;

import com.example.t505esports.evento.Evento;
import com.example.t505esports.evento.EventoRepository;
import com.example.t505esports.jugador.Jugador;
import com.example.t505esports.jugador.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepo;
    private final EventoRepository eventoRepo;
    private final JugadorRepository jugadorRepo;

    @Override
    public AsistenciaDTO registrarAsistencia(UUID eventoId, UUID jugadorId) {
        Evento evento = eventoRepo.findById(eventoId).orElseThrow();
        Jugador jugador = jugadorRepo.findById(jugadorId).orElseThrow();

        // Evitar duplicados
        if (asistenciaRepo.findByEventoAndJugador(evento, jugador).isPresent()) {
            throw new IllegalStateException("Ya se ha registrado la asistencia de este jugador");
        }

        Asistencia asistencia = Asistencia.builder()
                .evento(evento)
                .jugador(jugador)
                .fechaConfirmacion(LocalDateTime.now())
                .build();

        return AsistenciaMapper.toDTO(asistenciaRepo.save(asistencia));
    }

    @Override
    public List<AsistenciaDTO> listarPorEvento(UUID eventoId) {
        Evento evento = eventoRepo.findById(eventoId).orElseThrow();
        return asistenciaRepo.findByEvento(evento)
                .stream()
                .map(AsistenciaMapper::toDTO)
                .toList();
    }

    @Override
    public List<AsistenciaDTO> listarPorJugador(UUID jugadorId) {
        Jugador jugador = jugadorRepo.findById(jugadorId).orElseThrow();
        return asistenciaRepo.findByJugador(jugador)
                .stream()
                .map(AsistenciaMapper::toDTO)
                .toList();
    }
}
