import java.util.ArrayList;
import java.util.List;

public class Partido {
    private int fecha;
    private int horario;
    private int duracion;
    private int tiempoAdicional;
    private Estadio seDesarrolla;
    private Fase correspondeFase;
    private Participacion equipoLocal;
    private Participacion equipoVisitante;
    private List<Arbitraje> arbitrajes;
    private List<Evento> eventos;

    //Constructores
    public Partido() {
        this.arbitrajes = new ArrayList<Arbitraje>();
        this.eventos = new ArrayList<Evento>();
    }

    public Partido(int fecha, int horario, int duracion, int tiempoAdicional,
                   Estadio seDesarrolla, Fase correspondeFase,
                   Participacion equipoLocal, Participacion equipoVisitante) {
        this.fecha = fecha;
        this.horario = horario;
        this.duracion = duracion;
        this.tiempoAdicional = tiempoAdicional;
        this.seDesarrolla = seDesarrolla;
        this.correspondeFase = correspondeFase;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.eventos = new ArrayList<Evento>();
    }
    //Getters y Setter

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

    public Estadio getSeDesarrolla() {
        return seDesarrolla;
    }

    public void setSeDesarrolla(Estadio seDesarrolla) {
        this.seDesarrolla = seDesarrolla;
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
    }

    public Participacion getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Participacion equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public List<Arbitraje> getArbitrajes() {
        return arbitrajes;
    }

    public void setArbitrajes(List<Arbitraje> arbitrajes) {
        this.arbitrajes = arbitrajes;
    }

    public void agregarArbitraje(Arbitraje arbitraje) {
        this.arbitrajes.add(arbitraje);
        arbitraje.setPartido(this);
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void agregarEvento(TipoEvento tipo, int minuto, Jugador jugador) {
        Evento nuevoEvento = new Evento(tipo, minuto);
        nuevoEvento.setInvolucraJugador(jugador); // Asegura el vínculo bidireccional
        this.eventos.add(nuevoEvento);
    }
}
