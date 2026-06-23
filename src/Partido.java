import java.util.ArrayList;
import java.util.List;

/**
 * Representa un partido del torneo, con su informacion logistica
 * (fecha, horario, estadio, fase), los equipos local y visitante, el equipo
 * de arbitraje y los eventos registrados.
 */
public class Partido {
    private int fecha;
    private int horario;
    private int duracion;
    private int tiempoAdicional;
    private Estadio seDesarrollaEn;
    private Fase correspondeFase;
    private Participacion equipoLocal;
    private Participacion equipoVisitante;
    private List<Arbitraje> arbitrajes;
    private List<Evento> eventos;

    // Constructores

    /**
     * Crea una instancia vacia de {@code Partido}.
     */
    public Partido() {
        this.arbitrajes = new ArrayList<Arbitraje>();
        this.eventos = new ArrayList<Evento>();
    }

    /**
     * Crea una instancia de {@code Partido} con los datos indicados.
     *
     * @param fecha fecha
     * @param horario horario
     * @param duracion duracion
     * @param tiempoAdicional tiempoAdicional
     * @param seDesarrollaEn seDesarrollaEn
     * @param correspondeFase correspondeFase
     * @param equipoLocal equipoLocal
     * @param equipoVisitante equipoVisitante
     */
    public Partido(int fecha, int horario, int duracion, int tiempoAdicional,
                   Estadio seDesarrollaEn, Fase correspondeFase,
                   Participacion equipoLocal, Participacion equipoVisitante) {


        this.fecha = fecha;
        this.horario = horario;
        this.duracion = duracion;
        this.tiempoAdicional = tiempoAdicional;
        this.seDesarrollaEn = seDesarrollaEn;
        this.correspondeFase = correspondeFase;

        // validación de nulos
        this.equipoLocal = equipoLocal;
        if (this.equipoLocal != null) {
            this.equipoLocal.asociarPartido(this);
        }

        this.equipoVisitante = equipoVisitante;
        if (this.equipoVisitante != null) {
            this.equipoVisitante.asociarPartido(this);
        }

        this.eventos = new ArrayList<Evento>();
        this.arbitrajes = new ArrayList<Arbitraje>();
    }

    // Getters y Setters

    /**
     * Devuelve fecha.
     * @return fecha
     */
    public int getFecha() { return fecha; }

    /**
     * Establece fecha.
     *
     * @param fecha fecha
     */
    public void setFecha(int fecha) { this.fecha = fecha; }

    /**
     * Devuelve horario.
     * @return horario
     */
    public int getHorario() { return horario; }

    /**
     * Establece horario.
     *
     * @param horario horario
     */
    public void setHorario(int horario) { this.horario = horario; }

    /**
     * Devuelve duracion.
     * @return duracion
     */
    public int getDuracion() { return duracion; }

    /**
     * Establece duracion.
     *
     * @param duracion duracion
     */
    public void setDuracion(int duracion) { this.duracion = duracion; }

    /**
     * Devuelve tiempo adicional.
     * @return tiempo adicional
     */
    public int getTiempoAdicional() { return tiempoAdicional; }

    /**
     * Establece tiempo adicional.
     *
     * @param tiempoAdicional tiempoAdicional
     */
    public void setTiempoAdicional(int tiempoAdicional) { this.tiempoAdicional = tiempoAdicional; }

    /**
     * Devuelve se desarrolla en.
     * @return se desarrolla en
     */
    public Estadio getSeDesarrollaEn() { return seDesarrollaEn; }

    /**
     * Establece se desarrolla en.
     *
     * @param seDesarrollaEn seDesarrollaEn
     */
    public void setSeDesarrollaEn(Estadio seDesarrollaEn) { this.seDesarrollaEn = seDesarrollaEn; }

    /**
     * Devuelve corresponde fase.
     * @return corresponde fase
     */
    public Fase getCorrespondeFase() { return correspondeFase; }

    /**
     * Establece corresponde fase.
     *
     * @param correspondeFase correspondeFase
     */
    public void setCorrespondeFase(Fase correspondeFase) { this.correspondeFase = correspondeFase; }

    /**
     * Devuelve equipo local.
     * @return equipo local
     */
    public Participacion getEquipoLocal() {
        return equipoLocal;
    }

    /**
     * Establece equipo local.
     *
     * @param equipoLocal equipoLocal
     */
    public void setEquipoLocal(Participacion equipoLocal) {
        this.equipoLocal = equipoLocal;
        if (this.equipoLocal != null) {
            this.equipoLocal.asociarPartido(this);
        }
    }

    /**
     * Devuelve equipo visitante.
     * @return equipo visitante
     */
    public Participacion getEquipoVisitante() {
        return equipoVisitante;
    }

    /**
     * Establece equipo visitante.
     *
     * @param equipoVisitante equipoVisitante
     */
    public void setEquipoVisitante(Participacion equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
        if (this.equipoVisitante != null) {
            // Cambiado a asociarPartido
            this.equipoVisitante.asociarPartido(this);
        }
    }

    /**
     * Devuelve arbitrajes.
     * @return arbitrajes
     */
    public List<Arbitraje> getArbitrajes() {
        return arbitrajes;
    }

    /**
     * Establece arbitrajes.
     *
     * @param arbitrajes arbitrajes
     */
    public void setArbitrajes(List<Arbitraje> arbitrajes) {
        this.arbitrajes = arbitrajes;
    }

    /**
     * Agrega un objeto Arbitraje a la lista de arbitrajes de este partido.
     * @param arbitraje El objeto Arbitraje ya instanciado.
     */
    public void agregarArbitraje(Arbitraje arbitraje) {
        if(arbitraje !=null){
            this.arbitrajes.add(arbitraje);
            arbitraje.setPartido(this);
        }
    }

    /**
     * Devuelve eventos.
     * @return eventos
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * Establece eventos.
     *
     * @param eventos eventos
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    /**
     * Agrega un evento al partido validando que el jugador involucrado
     * pertenezca a alguno de los dos equipos.
     *
     * @param evento evento
     * @throws JugadorNoPerteneceAlPartidoException si la validacion correspondiente falla
     */
    public void agregarEvento(Evento evento) throws JugadorNoPerteneceAlPartidoException {
        Jugador jugadorImplicado = evento.getJugador();

        boolean juegaLocal = false;
        if (this.equipoLocal != null && this.equipoLocal.getSeleccion() != null) {
            juegaLocal = this.equipoLocal.getSeleccion().getJugadores().contains(jugadorImplicado);
        }

        boolean juegaVisitante = false;
        if (this.equipoVisitante != null && this.equipoVisitante.getSeleccion() != null) {
            juegaVisitante = this.equipoVisitante.getSeleccion().getJugadores().contains(jugadorImplicado);
        }

        if (!juegaLocal && !juegaVisitante) {
            throw new JugadorNoPerteneceAlPartidoException("El jugador ingresado no forma parte de ninguno de los dos equipos de este partido.");
        }

        this.eventos.add(evento);
    }
}