
/**
 * Gestora de informes del sistema. Concentra los reportes solicitados:
 * tabla de posiciones por grupo, resultados por selección, ranking de
 * goleadores, informe disciplinario, ficha técnica de partido y estadísticas
 * de sedes.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeneradorInformes {
    /**
     * Gestora de delegaciones de la que obtiene países y selecciones.
     */
    private GestionDelegaciones gestionDelegaciones;
    /**
     * Gestora deportiva de la que obtiene partidos y grupos.
     */
    private OrganizacionDeportiva organizacionDeportiva;

    public GeneradorInformes(GestionDelegaciones gestionDelegaciones, OrganizacionDeportiva organizacionDeportiva) {
        this.gestionDelegaciones = gestionDelegaciones;
        this.organizacionDeportiva = organizacionDeportiva;
    }

    /**
     * Genera la tabla de posiciones de un grupo: las selecciones del grupo
     * ordenadas por puntos en forma descendente.
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
     * Calcula el puntaje total de una selección sumando los puntos obtenidos en
     * todos los grupos donde participa.
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
     * Genera el ranking de goleadores recorriendo los eventos de todos los
     * partidos y acumulando los goles por jugador, ordenado en forma descendente.
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
     * Genera el informe disciplinario de una selección, sumando amarillas y
     * rojas a lo largo de todos sus partidos.
     *
     * @return texto con el detalle de amonestaciones y expulsiones
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
        return "INFORME DISCIPLINARIO:  " + seleccion.getNombreFederacion()+ "\n"
                + " Total de Tarjetas Amarillas: " + amarillas +"\n" +" | Total de Tarjetas Rojas: " + rojas+"\n";
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
            ficha = ficha + local.getSeleccion().getNombreFederacion() + " (Local) - Goles: "
                    + local.cantidadGoles() + "\nAlineacion:\n";
            for (Jugador jugador : local.getSeleccion().getJugadores()) {
                ficha = ficha + "  - " + jugador.getNombre() + "\n";
            }
        }

        // Procesamos al Visitante
        Participacion visitante = partido.getEquipoVisitante();
        if (visitante != null && visitante.getSeleccion() != null) {
            ficha = ficha + visitante.getSeleccion().getNombreFederacion() + " (Visitante) - Goles: "
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

