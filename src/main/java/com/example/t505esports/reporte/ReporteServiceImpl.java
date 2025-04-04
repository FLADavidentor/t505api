package com.example.t505esports.reporte;

import com.example.t505esports.asistencia.Asistencia;
import com.example.t505esports.asistencia.AsistenciaRepository;
import com.example.t505esports.jugador.Jugador;
import com.example.t505esports.jugador.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final JugadorRepository jugadorRepo;
    private final AsistenciaRepository asistenciaRepo;

    @Override
    public List<ReporteAsistenciaDTO> generarReporteGeneral() {
        List<Jugador> jugadores = jugadorRepo.findAll();

        return jugadores.stream().map(j -> {
            int asignados = j.getEventos().size();
            int asistidos = (int) asistenciaRepo.findByJugador(j).stream()
                    .map(Asistencia::getEvento)
                    .distinct()
                    .count();

            double porcentaje = asignados == 0 ? 0.0 : (asistidos * 100.0 / asignados);

            return new ReporteAsistenciaDTO(
                    j.getId(),
                    j.getNombre(),
                    j.getRol(),
                    asignados,
                    asistidos,
                    Math.round(porcentaje * 10.0) / 10.0
            );
        }).collect(Collectors.toList());
    }
}
