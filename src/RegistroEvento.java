import java.util.ArrayList;
import java.util.List;

/**
 * Clase Gestora encargada del registro de eventos de campo durante los partidos.
 * Registra los Eventos en tiempo real detallando el minuto y el jugador involucrado[cite: 34].
 */
public class RegistroEvento {

    private List<Evento> eventosRegistrados;

    // Constructor: Nace vacío
    public RegistroEvento() {
        this.eventosRegistrados = new ArrayList<>();
    }

    // Método Creador: Recibe los datos crudos, hace el "new", valida y crea
    /**
     * Registra un nuevo evento vinculando un jugador y un partido.
     * @param partido el partido donde ocurre el evento.
     * @param tipo el tipo de evento registrado.
     * @param minuto el minuto exacto del suceso.
     * @param jugador el jugador implicado.
     * @return el evento instanciado.
     * @throws JugadorNoPerteneceAlPartidoException si el jugador no forma parte del partido.
     */
    public Evento registrarEvento(Partido partido, TipoEvento tipo, int minuto, Jugador jugador)
            throws JugadorNoPerteneceAlPartidoException {

        if (partido == null || tipo == null || jugador == null) {
            throw new JugadorNoPerteneceAlPartidoException("Datos incompletos: partido, tipo de evento y jugador no pueden ser nulos.");
        }

        boolean pertenece = false;

        if (partido.getEquipoLocal() != null && partido.getEquipoLocal().getSeleccion() != null) {
            for (Jugador jugadorDeSeleccion : partido.getEquipoLocal().getSeleccion().getJugadores()) {
                if (jugadorDeSeleccion.equals(jugador)) pertenece = true;
            }
        }

        if (!pertenece && partido.getEquipoVisitante() != null && partido.getEquipoVisitante().getSeleccion() != null) {
            for (Jugador jugadorDeSeleccion : partido.getEquipoVisitante().getSeleccion().getJugadores()) {
                if (jugadorDeSeleccion.equals(jugador)) pertenece = true;
            }
        }

        if (!pertenece) {
            throw new JugadorNoPerteneceAlPartidoException("El jugador " + jugador.getNombre() + " no pertenece a ninguna de las selecciones del partido.");
        }

        // LA GESTORA FABRICA EL EVENTO (Cumpliendo la corrección del tutor)
        Evento nuevoEvento = new Evento(tipo, minuto, jugador);

        // Orquestación de bidireccionalidad
        jugador.agregarEvento(nuevoEvento);
        partido.agregarEvento(nuevoEvento);
        this.eventosRegistrados.add(nuevoEvento);

        return nuevoEvento;
    }

    // 3. Getters y Setters completos
    /**
     * Obtiene la lista completa de eventos.
     * @return la lista de eventos registrados.
     */
    public List<Evento> getEventosRegistrados() { return this.eventosRegistrados; }
    /**
     * Establece una nueva lista de eventos.
     * @param eventosRegistrados la nueva lista de eventos a establecer.
     */
    public void setEventosRegistrados(List<Evento> eventosRegistrados) { this.eventosRegistrados = eventosRegistrados; }
}