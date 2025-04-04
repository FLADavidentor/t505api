package com.example.t505esports.jugador;

import com.example.t505esports.config.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository jugadorRepository;

    @Override
    public JugadorDTO crearJugador(JugadorDTO dto) {
        return JugadorMapper.toDTO(jugadorRepository.save(JugadorMapper.toEntity(dto)));
    }

    @Override
    public JugadorDTO editarJugador(JugadorDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.id())
                .orElseThrow(() -> new BusinessException("Jugador no encontrado"));

        jugador.setNombre(dto.nombre());
        jugador.setRol(dto.rol());

        return JugadorMapper.toDTO(jugadorRepository.save(jugador));
    }


    @Override
    public List<JugadorDTO> obtenerTodos() {
        return jugadorRepository.findAll()
                .stream()
                .map(JugadorMapper::toDTO)
                .toList();
    }

    @Override
    public JugadorDTO obtenerPorId(UUID id) {
        return jugadorRepository.findById(id)
                .map(JugadorMapper::toDTO)
                .orElseThrow(() -> new BusinessException("Jugador no encontrado"));
    }

    @Override
    public void eliminarPorId(UUID id) {
        jugadorRepository.deleteById(id);
    }
}

