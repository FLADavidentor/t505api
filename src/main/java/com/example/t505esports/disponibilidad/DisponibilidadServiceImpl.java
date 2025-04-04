package com.example.t505esports.disponibilidad;

import com.example.t505esports.config.BusinessException;
import com.example.t505esports.jugador.Jugador;
import com.example.t505esports.jugador.JugadorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {

    private final DisponibilidadRepository disponibilidadRepo;
    private final JugadorRepository jugadorRepo;

    @Override
    public DisponibilidadDTO crear(DisponibilidadDTO dto) {
        Jugador jugador = jugadorRepo.findById(dto.jugadorId())
                .orElseThrow(() -> new BusinessException("Jugador no encontrado con ID: " + dto.jugadorId()));

        Disponibilidad disponibilidad = DisponibilidadMapper.toEntity(dto, jugador);
        Disponibilidad guardada = disponibilidadRepo.save(disponibilidad);

        return DisponibilidadMapper.toDTO(guardada);
    }

    @Override
    @Transactional
    public void guardarMultiples(List<DisponibilidadDTO> lista) {
        if (lista.isEmpty()) return;

        String jugadorId = lista.get(0).jugadorId().toString();
        Jugador jugador = jugadorRepo.findById(UUID.fromString(jugadorId))
                .orElseThrow(() -> new BusinessException("Jugador no encontrado"));

        // Eliminar disponibilidades previas
        disponibilidadRepo.deleteByJugadorId(UUID.fromString(jugadorId));

        // Convertir y guardar nuevas disponibilidades
        List<Disponibilidad> nuevas = lista.stream()
                .map(dto -> DisponibilidadMapper.toEntity(dto, jugador))
                .toList();

        disponibilidadRepo.saveAll(nuevas);
    }

    @Override
    public List<DisponibilidadDTO> listar() {
        return disponibilidadRepo.findAll()
                .stream()
                .map(DisponibilidadMapper::toDTO)
                .toList();
    }

    @Override
    public List<DisponibilidadDTO> listarPorJugador(String jugadorId) {
        Jugador jugador = jugadorRepo.findById(UUID.fromString(jugadorId))
                .orElseThrow(() -> new BusinessException("Jugador no encontrado con ID: " + jugadorId));

        return disponibilidadRepo.findByJugador(jugador).stream()
                .map(DisponibilidadMapper::toDTO)
                .toList();
    }

}
