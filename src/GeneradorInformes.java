
/**
 * Gestora de informes del sistema.
 * reportes solicitados:
 * tabla de posiciones por grupo, resultados por selección, ranking de
 * goleadores, informe disciplinario, ficha técnica de partido y estadísticas
 * de sedes.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Gestora encargada de recopilar los datos y generar los reportes del Mundial.
 */

public class GeneradorInformes {
    private GestionDelegaciones gestionDelegaciones;

    private OrganizacionDeportiva organizacionDeportiva;
    /**
     * Crea una instancia del generador de informes.
     * @param gestionDelegaciones Gestora de las delegaciones.
     * @param organizacionDeportiva Gestora de los partidos y grupos.
     */

    public GeneradorInformes(GestionDelegaciones gestionDelegaciones, OrganizacionDeportiva organizacionDeportiva) {
        this.gestionDelegaciones = gestionDelegaciones;
        this.organizacionDeportiva = organizacionDeportiva;
    }



    /**
     * Genera la tabla de posiciones de un grupo.
     * @param grupo El grupo a evaluar.
     * @return Una lista ordenada con los puntos de cada selección.
     */

    public List<RegistroPosicion> tablaPosicionesPorGrupo(Grupo grupo) {
        List<RegistroPosicion> tabla = new ArrayList<RegistroPosicion>();
        if (grupo == null) {
            return tabla;
        }
        for (Seleccion seleccion : grupo.getSelecciones()) {
            int puntos = grupo.obtenerPuntos(seleccion);
            tabla.add(new RegistroPosicion(seleccion, puntos));
        }
        Collections.sort(tabla, new Comparator<RegistroPosicion>() {
            @Override
            public int compare(RegistroPosicion a, RegistroPosicion b) {
                return Integer.compare(b.getPuntos(), a.getPuntos());
            }
        });
        return tabla;
    }


    /**
     * Calcula el puntaje total acumulado por una selección.
     * @param seleccion La selección a calcular.
     * @return El puntaje entero total.
     */
    public int puntajeTotalSeleccion(Seleccion seleccion) {
        int total = 0;
        if (seleccion == null) {
            return total;
        }
        for (Grupo grupo : this.organizacionDeportiva.getGruposMundial()) {
            for (Seleccion seleccionDelGrupo : grupo.getSelecciones()) {
                if (seleccionDelGrupo.equals(seleccion)) {
                    total = total + grupo.obtenerPuntos(seleccion);
                }
            }
        }
        return total;
    }

    /**
     * Genera el detalle de resultados e instancias alcanzadas por una selección:
     * una línea por cada partido disputado (rival, marcador y resultado) y una
     * línea final con el puntaje total acumulado en fase de grupos.
     *
     * @param seleccion selección a consultar
     * @return lista de líneas con el detalle; vacía si no disputó partidos
     */
    public List<String> resultadosPorSeleccion(Seleccion seleccion) {
        List<String> resultados = new ArrayList<String>();
        if (seleccion == null) {
            return resultados;
        }

        for (Participacion parti : seleccion.getParticipaciones()) {
            Partido partido = parti.getPartido();
            if (partido == null) {
                continue;
            }

            Participacion rival = null;
            if (partido.getEquipoLocal().equals(parti)) {
                rival = partido.getEquipoVisitante();
            } else if (partido.getEquipoVisitante().equals(parti)) {
                rival = partido.getEquipoLocal();
            }
            if (rival == null) {
                continue;
            }

            int goles = parti.cantidadGoles();
            int golesRival = rival.cantidadGoles();

            String resultado;
            if (goles > golesRival) {
                resultado = "Victoria";
            } else if (goles == golesRival) {
                resultado = "Empate";
            } else {
                resultado = "Derrota";
            }

            String fase = (partido.getCorrespondeFase() != null)
                    ? partido.getCorrespondeFase().getNombre().toString()
                    : "Sin fase";

            resultados.add("[" + fase + "] vs "
                    + rival.getSeleccion().getPais().getNombre()
                    + " " + goles + "-" + golesRival
                    + " (" + resultado + ")");
        }

        // Línea final con el puntaje total (reutiliza el método int existente)
        resultados.add("Puntaje total: " + puntajeTotalSeleccion(seleccion) + " pts");

        return resultados;
    }

    /**
     * Genera el ranking histórico de goleadores del torneo.
     * @return Una lista ordenada de jugadores y sus goles.
     */
    public List<RegistroGoleador> rankingGoleadores() {
        List<RegistroGoleador> ranking = new ArrayList<RegistroGoleador>();

        for (Partido partido : this.organizacionDeportiva.getTodosLosPartidos()) {
            for (Evento evento : partido.getEventos()) {
                if (evento.getTipo() == TipoEvento.GOL || evento.getTipo()== TipoEvento.PENAL_CONVERTIDO) {
                    Jugador autor = evento.getJugador();
                    if (autor != null) {
                        RegistroGoleador registro = this.buscarRegistroGoleador(ranking, autor);
                        if (registro == null) {
                            ranking.add(new RegistroGoleador(autor, 1));
                        } else {
                            registro.sumarGol();
                        }
                    }
                }
            }
        }
        Collections.sort(ranking, new Comparator<RegistroGoleador>() {
            @Override
            public int compare(RegistroGoleador a, RegistroGoleador b) {
                return Integer.compare(b.getGoles(), a.getGoles());
            }
        });
        return ranking;
    }

    /**
     * Busca en el acumulador la fila correspondiente a un jugador.
     *
     * @return la fila existente, o {@code null} si el jugador aún no figura
     */
    private RegistroGoleador buscarRegistroGoleador(List<RegistroGoleador> ranking, Jugador jugador) {
        for (RegistroGoleador registro : ranking) {
            if (registro.getJugador().equals(jugador)) {
                return registro;
            }
        }
        return null;
    }

    /**
     * Genera un reporte con las tarjetas de un equipo.
     * @param seleccion La selección a consultar.
     * @return Un String con el texto del reporte.
     */
    public String informeDisciplinarioSeleccion(Seleccion seleccion) {
        int amarillas = 0;
        int rojas = 0;
        if (seleccion == null) {
            return "Selección nula.";
        }
        for (Partido partido : this.organizacionDeportiva.getTodosLosPartidos()) {
            Participacion local = partido.getEquipoLocal();
            if (local != null && local.getSeleccion()!= null && local.getSeleccion().equals(seleccion)) {
                amarillas = amarillas + local.cantidadTarjAmarillas();
                rojas = rojas + local.cantidadTarjRojas();
            }
            Participacion visitante = partido.getEquipoVisitante();
            if (visitante != null && visitante.getSeleccion() != null && visitante.getSeleccion().equals(seleccion)) {
                amarillas += visitante.cantidadTarjAmarillas();
                rojas += visitante.cantidadTarjRojas();
            }
        }
        return "INFORME DISCIPLINARIO:  " + seleccion.getPais().getNombre()+ "\n"
                + " Total de Tarjetas Amarillas: " + amarillas +"\n" +" | Total de Tarjetas Rojas: " + rojas+"\n";
    }

    /**
     * Genera el informe disciplinario de un jugador, contando las tarjetas
     * (amarillas, rojas y dobles amarillas) que registró a lo largo de todos
     * los partidos del torneo.
     *
     * @param jugador jugador a consultar
     * @return texto con el detalle de tarjetas del jugador
     */
    public String informeDisciplinarioJugador(Jugador jugador) {
        if (jugador == null) {
            return "Jugador nulo.";
        }
        int amarillas = 0;
        int rojas = 0;
        int doblesAmarillas = 0;

        for (Partido partido : this.organizacionDeportiva.getTodosLosPartidos()) {
            for (Evento evento : partido.getEventos()) {
                if (evento.getJugador() != null && evento.getJugador().equals(jugador)) {
                    if (evento.getTipo() == TipoEvento.TARJETA_AMARILLA) {
                        amarillas++;
                    } else if (evento.getTipo() == TipoEvento.TARJETA_ROJA) {
                        rojas++;
                    } else if (evento.getTipo() == TipoEvento.DOBLE_AMARILLA) {
                        doblesAmarillas++;
                    }
                }
            }
        }

        return "INFORME DISCIPLINARIO DEL JUGADOR: " + jugador.getNombre() + "\n"
                + " Tarjetas Amarillas: " + amarillas + "\n"
                + " Dobles Amarillas: " + doblesAmarillas + "\n"
                + " Tarjetas Rojas: " + rojas + "\n";
    }

    /**
     * Genera la ficha técnica completa de un partido: alineaciones, resultado
     * por selección y detalle de eventos.
     *
     * @param partido partido a detallar
     * @return texto con la ficha técnica
     */
    public String fichaTecnicaPartido(Partido partido) {
        if (partido == null) {
            return "Partido nulo.";
        }

        // Iniciamos el texto con el título
        String ficha = "=== FICHA TECNICA ===\n";

        // Procesamos al Local
        Participacion local = partido.getEquipoLocal();
        if (local != null && local.getSeleccion() != null) {
            ficha = ficha + local.getSeleccion().getPais().getNombre() + " (Local) - Goles: "
                    + local.cantidadGoles() + "\nAlineacion:\n";
            for (Jugador jugador : local.getSeleccion().getJugadores()) {
                ficha = ficha + "  - " + jugador.getNombre() + "\n";
            }
        }

        // Procesamos al Visitante
        Participacion visitante = partido.getEquipoVisitante();
        if (visitante != null && visitante.getSeleccion() != null) {
            ficha = ficha + visitante.getSeleccion().getPais().getNombre() + " (Visitante) - Goles: "
                    + visitante.cantidadGoles() + "\nAlineacion:\n";
            for (Jugador jugador : visitante.getSeleccion().getJugadores()) {
                ficha = ficha + "  - " + jugador.getNombre() + "\n";
            }
        }

        // Procesamos los Eventos
        ficha = ficha + "Eventos:\n";
        for (Evento evento : partido.getEventos()) {
            String nombreJugador = (evento.getJugador() != null) ? evento.getJugador().getNombre() : "Sin jugador";
            ficha = ficha + "  Min " + evento.getMinuto() + ": " + evento.getTipo() + " - " + nombreJugador + "\n";
        }

        return ficha;
    }

    /**
     * Cuenta la cantidad de partidos jugados en un estadio.
     *
     * @param estadio estadio a evaluar
     * @return cantidad de partidos del estadio (0 si es {@code null})
     */
    public int partidosEnEstadio(Estadio estadio) {
        if (estadio == null) {
            return 0;
        }
        return estadio.getPartidos().size();
    }

    /**
     * Cuenta la cantidad de partidos jugados en una ciudad, sumando los de
     * todos los estadios de la sede.
     *
     * @param sede sede (ciudad) a evaluar
     * @return cantidad total de partidos de la ciudad (0 si es {@code null})
     */
    public int partidosEnCiudad(Sede sede) {
        int total = 0;
        if (sede == null) {
            return total;
        }
        for (Estadio estadio : sede.getEstadios()) {
            total = total + estadio.getPartidos().size();
        }
        return total;
    }
}

