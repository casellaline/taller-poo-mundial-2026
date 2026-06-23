import java.util.ArrayList;
import java.util.List;

/**
 * Clase Gestora encargada del registro de eventos de campo durante los partidos.
 * Registra los Eventos en tiempo real detallando el minuto y el jugador involucrado[cite: 34].
 */
public class RegistroEvento {

    private List<Evento> eventosRegistrados;

    // Constructores
    /**
     * Crea una instancia vacia de {@code RegistroEvento}.
     */
    public RegistroEvento() {
        this.eventosRegistrados = new ArrayList<>();
    }

    /**
     * Registra un evento en un partido validando que el jugador pertenezca
     * al partido.
     *
     * @param partido partido en el que ocurre el evento
     * @param tipo tipo de evento
     * @param minuto minuto del suceso
     * @param jugador jugador involucrado
     * @return el evento creado y registrado
     * @throws JugadorNoPerteneceAlPartidoException si los datos son nulos o el
     *         jugador no participa en el partido
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

        Evento nuevoEvento = new Evento(tipo, minuto, jugador);

        // bidireccionalidad
        jugador.agregarEvento(nuevoEvento);
        partido.agregarEvento(nuevoEvento);
        this.eventosRegistrados.add(nuevoEvento);

        return nuevoEvento;
    }

    // Getters y Setters

    /**
     * Devuelve eventos registrados.
     * @return eventos registrados
     */
    public List<Evento> getEventosRegistrados() { return this.eventosRegistrados; }

    /**
     * Establece eventos registrados.
     *
     * @param eventosRegistrados eventosRegistrados
     */
    public void setEventosRegistrados(List<Evento> eventosRegistrados) { this.eventosRegistrados = eventosRegistrados; }
}