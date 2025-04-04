package com.example.t505esports.evento;

import com.example.t505esports.config.BusinessException;
import com.example.t505esports.discord.DiscordService;
import com.example.t505esports.disponibilidad.Disponibilidad;
import com.example.t505esports.jugador.Jugador;
import com.example.t505esports.jugador.RolJugador;
import com.example.t505esports.disponibilidad.DisponibilidadRepository;
import com.example.t505esports.jugador.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepo;
    private final JugadorRepository jugadorRepo;
    private final DisponibilidadRepository disponibilidadRepo;
    private final DiscordService discordService;


    @Override
    public EventoDTO crearEvento(EventoDTO dto) {
        // Obtener día del evento
        // ✅ Convertir inicio y fin a la zona horaria local (ej. América/Managua o tu país)
        ZoneId zonaLocal = ZoneId.of("America/Managua"); // Cambiá si estás en otra
        ZonedDateTime zonedInicio = dto.inicio().atZone(ZoneId.of("UTC")).withZoneSameInstant(zonaLocal);
        ZonedDateTime zonedFin = dto.fin().atZone(ZoneId.of("UTC")).withZoneSameInstant(zonaLocal);

        LocalTime horaInicioEvento = zonedInicio.toLocalTime();
        LocalTime horaFinEvento = zonedFin.toLocalTime();
        DayOfWeek diaEvento = zonedInicio.getDayOfWeek();

        DayOfWeek dia = zonedInicio.getDayOfWeek(); // ✅ ya está en hora local correcta
        List<Disponibilidad> disponibles = disponibilidadRepo.findByDia(dia);
        System.out.println("🧠 Verificando disponibilidades de los jugadores para el evento:");
        System.out.println("Evento desde: " + dto.inicio());
        System.out.println("Evento hasta: " + dto.fin());

        disponibles.forEach(d -> {
            System.out.println("Jugador: " + d.getJugador().getNombre() + " [" + d.getJugador().getRol() + "]");
            System.out.println("Día: " + d.getDia());
            System.out.println("Inicio: " + d.getHoraInicio());
            System.out.println("Fin: " + d.getHoraFin());
            System.out.println("---");
        });


        // Filtrar jugadores que están disponibles en el horario completo
        List<Jugador> candidatos = disponibles.stream()
                .filter(d -> d.getDia() == diaEvento &&
                        !d.getHoraInicio().isAfter(horaInicioEvento) &&
                        !d.getHoraFin().isBefore(horaFinEvento))

                .map(Disponibilidad::getJugador)
                .distinct()
                .toList();

        // Agrupar por rol
        Map<RolJugador, List<Jugador>> porRol = candidatos.stream()
                .collect(Collectors.groupingBy(Jugador::getRol));

        // Verificar requisitos mínimos

        List<String> faltantes = new ArrayList<>();
        if (porRol.getOrDefault(RolJugador.DAÑO, List.of()).size() < 2) faltantes.add("DAÑO");
        if (porRol.getOrDefault(RolJugador.SOPORTE, List.of()).size() < 2) faltantes.add("SOPORTE");
        if (porRol.getOrDefault(RolJugador.TANQUE, List.of()).size() < 1) faltantes.add("TANQUE");

        if (!faltantes.isEmpty()) {
            throw new BusinessException("Faltan jugadores disponibles en los siguientes roles: " + String.join(", ", faltantes));
        }



        // Selección básica (podés mejorar con algoritmo de turnos más adelante)
        Set<Jugador> asignados = new HashSet<>();
        asignados.addAll(porRol.get(RolJugador.DAÑO).subList(0, 2));
        asignados.addAll(porRol.get(RolJugador.SOPORTE).subList(0, 2));
        asignados.add(porRol.get(RolJugador.TANQUE).get(0));

        Evento evento = EventoMapper.toEntity(dto, asignados);
        Evento guardado = eventoRepo.save(evento);


        String asunto = "📅 Nuevo evento: " + guardado.getTitulo();
        String cuerpo = """
                Se ha creado un nuevo evento.

                Tipo: %s
                Fecha: %s
                Hora: %s - %s

                ¡No faltes!
                """.formatted(
                guardado.getTipo(),
                guardado.getInicio().toLocalDate(),
                guardado.getInicio().toLocalTime(),
                guardado.getFin().toLocalTime()
        );

        discordService.notificarEvento("""
        📣 Se ha creado un nuevo evento:
        📝 *%s* (%s)
        📅 Fecha: %s
        🕒 Hora: %s - %s
        """.formatted(
                guardado.getTitulo(),
                guardado.getTipo(),
                guardado.getInicio().toLocalDate(),
                guardado.getInicio().toLocalTime(),
                guardado.getFin().toLocalTime()
        ));

        return EventoMapper.toDTO(guardado);

    }

    @Override
    public List<EventoDTO> listarEventos() {
        return eventoRepo.findAll().stream()
                .map(EventoMapper::toDTO)
                .toList();
    }
}
