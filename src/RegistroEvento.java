/**
 * Clase Gestora encargada del registro de eventos de campo durante los partidos (Minutos).
 * Valida que el jugador involucrado pertenezca a alguna de las dos selecciones
 * que disputan el partido antes de registrar el evento.
 */
import java.util.ArrayList;
import java.util.List;

public class RegistroEvento {

    private List<Evento> eventosRegistrados;

    //Constructor
    public RegistroEvento(){
        this.eventosRegistrados = new ArrayList<Evento>();
    }
    /**
     * Registra un evento en un partido validando que el jugador pertenezca a
     * alguna de las selecciones participantes. Aplica la bidireccionalidad
     * agregando el evento al partido y a la gestora.
     */
    public void registrarEvento(Partido partido, Evento evento, Jugador jugador)
    throws JugadorNoPerteneceAlPartidoException {
        if(partido == null || evento == null || jugador == null){
            throw new JugadorNoPerteneceAlPartidoException(
                    "Datos incompletos: partido, evento y jugador no pueden ser null.");
        }
        boolean pertenece = false;
        if (partido.getEquipoLocal() != null && partido.getEquipoLocal().getSeleccion() != null) {
            for (Jugador jugadorDeSeleccion : partido.getEquipoLocal().getSeleccion().getJugadores()) {
                if (jugadorDeSeleccion.equals(jugador)) {
                    pertenece = true;
                }
            }
        }

        // Si no estaba en la Local, revisamos en la Selección Visitante
        if (!pertenece && partido.getEquipoVisitante() != null && partido.getEquipoVisitante().getSeleccion() != null) {
            for (Jugador jugadorDeSeleccion : partido.getEquipoVisitante().getSeleccion().getJugadores()) {
                if (jugadorDeSeleccion.equals(jugador)) {
                    pertenece = true;
                }
            }
        }
        if (!pertenece) {
            throw new JugadorNoPerteneceAlPartidoException(
                    "El jugador " + jugador.getNombre() + " no pertenece a ninguna de las selecciones del partido.");
        }

        // 1. Orquestación tradicional desde la Gestora:
        // Le avisamos al jugador que participó en este evento
        jugador.agregarEvento(evento);

        // Le pasamos el MISMO evento al partido
        partido.agregarEvento(evento);

        // Lo guardamos en el registro general
        this.eventosRegistrados.add(evento);
    }
    public List<Evento> getEventosRegistrados() { return this.eventosRegistrados; }
    }



