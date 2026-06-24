import java.util.ArrayList;
import java.util.List;

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
    public Partido() {
        this.arbitrajes = new ArrayList<Arbitraje>();
        this.eventos = new ArrayList<Evento>();
    }

    // Constructor
    public Partido(int fecha, int horario, int duracion, int tiempoAdicional,
                   Estadio seDesarrollaEn, Fase correspondeFase,
                   Participacion equipoLocal, Participacion equipoVisitante) {

        // 1. Asignación directa de tipos básicos
        this.fecha = fecha;
        this.horario = horario;
        this.duracion = duracion;
        this.tiempoAdicional = tiempoAdicional;
        this.seDesarrollaEn = seDesarrollaEn;
        this.correspondeFase = correspondeFase;

        // 2. Asignación directa de objetos + Validación de nulos (sin usar el setter)
        this.equipoLocal = equipoLocal;
        if (this.equipoLocal != null) {
            this.equipoLocal.asociarPartido(this); // Mantenemos la bidireccionalidad
        }

        this.equipoVisitante = equipoVisitante;
        if (this.equipoVisitante != null) {
            this.equipoVisitante.asociarPartido(this);
        }

        // 3. Declarado como List arriba, inicializado como ArrayList acá abajo
        this.eventos = new ArrayList<Evento>();
        this.arbitrajes = new ArrayList<Arbitraje>();
    }

    // Getters y Setters

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getTiempoAdicional() {
        return tiempoAdicional;
    }

    public void setTiempoAdicional(int tiempoAdicional) {
        this.tiempoAdicional = tiempoAdicional;
    }

    public Estadio getSeDesarrollaEn() {
        return seDesarrollaEn;
    }

    public void setSeDesarrollaEn(Estadio seDesarrollaEn) {
        this.seDesarrollaEn = seDesarrollaEn;
    }

    public Fase getCorrespondeFase() {
        return correspondeFase;
    }

    public void setCorrespondeFase(Fase correspondeFase) {
        this.correspondeFase = correspondeFase;
    }

    public Participacion getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Participacion equipoLocal) {
        this.equipoLocal = equipoLocal;
        if (this.equipoLocal != null) {
            this.equipoLocal.asociarPartido(this);
        }
    }

    public Participacion getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Participacion equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
        if (this.equipoVisitante != null) {
            // Cambiado a asociarPartido
            this.equipoVisitante.asociarPartido(this);
        }
    }

    public List<Arbitraje> getArbitrajes() {
        return arbitrajes;
    }

    public void setArbitrajes(List<Arbitraje> arbitrajes) {
        this.arbitrajes = arbitrajes;
    }

    /**
     * Agrega un objeto Arbitraje (la asignación de un rol a un árbitro)
     * a la lista de arbitrajes de este partido.
     *
     * @param arbitraje El objeto Arbitraje ya instanciado.
     */
    public void agregarArbitraje(Arbitraje arbitraje) {
        if (arbitraje != null) {
            this.arbitrajes.add(arbitraje);
            arbitraje.setPartido(this);
        }
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void agregarEvento(Evento evento) throws JugadorNoPerteneceAlPartidoException {

        // 1. Obtenemos el jugador que está dentro del evento que intentan registrar
        Jugador jugadorImplicado = evento.getJugador();

        // 2. Verificamos si el jugador pertenece a la selección del equipo local o visitante.
        // (Nota: Ajusta los "getSeleccion()" y "getJugadores()" según los nombres exactos de tus getters)
        boolean juegaLocal = false;
        if (this.equipoLocal != null && this.equipoLocal.getSeleccion() != null) {
            juegaLocal = this.equipoLocal.getSeleccion().getJugadores().contains(jugadorImplicado);
        }

        boolean juegaVisitante = false;
        if (this.equipoVisitante != null && this.equipoVisitante.getSeleccion() != null) {
            juegaVisitante = this.equipoVisitante.getSeleccion().getJugadores().contains(jugadorImplicado);
        }

        // 3. Si no juega ni de local ni de visitante, ¡Lanzamos la excepción!
        if (!juegaLocal && !juegaVisitante) {
            throw new JugadorNoPerteneceAlPartidoException("El jugador ingresado no forma parte de ninguno de los dos equipos de este partido.");
        }

        // 4. Si pasó la validación de arriba, entonces sí lo agregamos a la lista
        this.eventos.add(evento);
    }
}