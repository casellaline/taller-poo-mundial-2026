import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la organización deportiva: configuración de grupos y
 * fases, planificación de partidos y validación del equipo de arbitraje.
 */
public class OrganizacionDeportiva {
    private List<Partido> todosLosPartidos;
    private List<Grupo> gruposMundial;

    // Constructor

    /**
     * Crea una instancia de {@code OrganizacionDeportiva} con los datos indicados.
     *
     * @param todosLosPartidos todosLosPartidos
     * @param gruposMundial gruposMundial
     */
    public OrganizacionDeportiva(List<Partido> todosLosPartidos, List<Grupo> gruposMundial) {
        this.todosLosPartidos = todosLosPartidos;
        this.gruposMundial = gruposMundial;
    }

    // MÉTODOS CREADORES

    /**
     * Configura un nuevo grupo de la fase de grupos.
     *
     * @param identificacion identificacion
     * @param descripcion descripcion
     * @param fase fase
     * @return resultado de la operacion
     */
    public Grupo configurarGrupo(String identificacion, String descripcion, Fase fase) {
        Grupo nuevoGrupo = new Grupo(identificacion, descripcion, fase);
        this.gruposMundial.add(nuevoGrupo);
        return nuevoGrupo;
    }

    /**
     * Planifica un nuevo partido con sus datos logisticos y equipos.
     *
     * @param fecha fecha
     * @param horario horario
     * @param duracion duracion
     * @param tiempoAdicional tiempoAdicional
     * @param estadio estadio
     * @param fase fase
     * @param local local
     * @param visitante visitante
     * @return resultado de la operacion
     */
    public Partido planificarPartido(int fecha, int horario, int duracion, int tiempoAdicional,
                                     Estadio estadio, Fase fase,
                                     Participacion local, Participacion visitante) {
        Partido nuevoPartido = new Partido(fecha, horario, duracion, tiempoAdicional, estadio, fase, local, visitante);
        this.todosLosPartidos.add(nuevoPartido);
        return nuevoPartido;
    }
    /**
     * Busca un partido por su fecha.
     * @param fecha La fecha del partido a buscar (Ej. 20260615)
     * @return El objeto Partido si lo encuentra, o null si no existe.
     */
    public Partido buscarPartidoPorFecha(int fecha) {
        for (Partido partido : this.todosLosPartidos) {
            if (partido.getFecha() == fecha) {
                return partido;
            }
        }
        return null;
    }

    // Métodos para agregar objetos

    /**
     * Agrega un partido a la lista de partidos del torneo.
     *
     * @param partido partido
     */
    public void agregarPartido(Partido partido) {
        this.todosLosPartidos.add(partido);
    }

    /**
     * Agrega un grupo a la lista de grupos del torneo.
     *
     * @param grupo grupo
     */
    public void agregarGrupo(Grupo grupo) {
        this.gruposMundial.add(grupo);
    }

    // GETTERS Y SETTERS

    /**
     * Devuelve todos los partidos.
     * @return todos los partidos
     */
    public List<Partido> getTodosLosPartidos() { return this.todosLosPartidos; }

    /**
     * Establece todos los partidos.
     *
     * @param todosLosPartidos todosLosPartidos
     */
    public void setTodosLosPartidos(List<Partido> todosLosPartidos) { this.todosLosPartidos = todosLosPartidos; }

    /**
     * Devuelve grupos mundial.
     * @return grupos mundial
     */
    public List<Grupo> getGruposMundial() { return this.gruposMundial; }

    /**
     * Establece grupos mundial.
     *
     * @param gruposMundial gruposMundial
     */
    public void setGruposMundial(List<Grupo> gruposMundial) { this.gruposMundial = gruposMundial; }

    // VALIDACIÓN
    /**
     * Indica si el partido tiene asignado un arbitro con rol principal.
     *
     * @param partido partido
     * @return resultado de la operacion
     */
    public boolean tieneArbitroPrincipal(Partido partido) {
        if (partido == null) {
            return false;
        }
        if (partido.getArbitrajes() != null) {
            for (Arbitraje arbitraje : partido.getArbitrajes()) {
                if (arbitraje != null && arbitraje.getRol() == CategoriaArbitro.PRINCIPAL) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Crea una fase del torneo a partir del nombre indicado. La instanciación
     * queda encapsulada en la gestora.
     *
     * @param nombre nombre de la fase
     * @return la fase creada
     */
    public Fase crearFase(NombreFase nombre) {
        return new Fase(nombre);
    }

    /**
     * Crea la participación de una selección en un partido (local o visitante)
     * y la vincula con la selección. La instanciación queda encapsulada en la
     * gestora.
     *
     * @param esLocal indica si la selección juega de local
     * @param seleccion selección participante
     * @return la participación creada
     */
    public Participacion crearParticipacion(boolean esLocal, Seleccion seleccion) {
        Participacion participacion = new Participacion(esLocal, seleccion);
        if (seleccion != null) {
            seleccion.agregarParticipacion(participacion);
        }
        return participacion;
    }

    /**
     * Asigna un arbitraje a un partido creando el objeto {@code Arbitraje}
     * internamente. Si el rol no es principal, valida que el partido ya tenga
     * un árbitro principal; en todos los casos impide que exista más de un
     * árbitro principal por partido.
     *
     * @param categoria categoría del arbitraje (PRINCIPAL, asistente, etc.)
     * @param arbitro árbitro a asignar
     * @param partido partido al que se asigna
     * @return el arbitraje creado y asignado
     * @throws PartidoSinArbitroPrincipalException si se asigna un rol no
     *         principal a un partido que aún no tiene árbitro principal
     *
     */
    public Arbitraje asignarArbitraje(CategoriaArbitro categoria, Arbitro arbitro, Partido partido)
            throws PartidoSinArbitroPrincipalException {
        if (categoria != CategoriaArbitro.PRINCIPAL) {
            this.validarArbitroPrincipal(partido);
        }
        Arbitraje nuevoArbitraje = new Arbitraje(categoria, arbitro, partido);
        partido.agregarArbitraje(nuevoArbitraje);
        return nuevoArbitraje;
    }

    /**
     * Registra un evento en un partido creando el objeto {@code Evento}
     * internamente y validando que el jugador pertenezca al partido.
     *
     * @param partido partido en el que ocurre el evento
     * @param tipo tipo de evento
     * @param minuto minuto del suceso
     * @param jugador jugador involucrado
     * @return el evento creado y registrado
     * @throws JugadorNoPerteneceAlPartidoException si el jugador no participa
     *         en el partido
     */
    public Evento registrarEvento(Partido partido, TipoEvento tipo, int minuto, Jugador jugador)
            throws JugadorNoPerteneceAlPartidoException {
        Evento nuevoEvento = new Evento(tipo, minuto, jugador);
        partido.agregarEvento(nuevoEvento);
        return nuevoEvento;
    }


    /**
     * Valida que un partido tenga un equipo de arbitraje válido (con árbitro
     * principal). Lanza excepción si no lo cumple.
     *
     * @param partido partido a validar
     * @throws PartidoSinArbitroPrincipalException si el partido no tiene un
     *         árbitro con rol principal asignado
     */
    public void validarArbitroPrincipal(Partido partido) throws PartidoSinArbitroPrincipalException {
        if (!this.tieneArbitroPrincipal(partido)) {
            throw new PartidoSinArbitroPrincipalException("El partido no tiene asignado un árbitro con rol Principal.");
        }
    }



}