public class Partido {
    private int fecha;
    private int horario;
    private int duracion;
    private int tiempoAdicional;
    private Estadio seDesarrolla;
    private Fase correspondeFase;
    private Arbitro cuentaConArbitro;
    private Arbitraje arbitraje;
    private Evento evento;
    private Participacion equipoLocal;
    private Participacion equipoVisitante;
    //Constructores

    public Partido(int fecha, int horario, int duracion, int tiempoAdicional, Estadio seDesarrolla, Fase correspondeFase, TipoEvento tipo, int minuto, Participacion equipoLocal, Participacion equipoVisitante ) {
        this.fecha = fecha;
        this.horario=horario;
        this.duracion=duracion;
        this.tiempoAdicional=tiempoAdicional;
        this.seDesarrolla=seDesarrolla;
        this.correspondeFase=correspondeFase;
        this.evento= new Evento(tipo, minuto);
        this.equipoLocal=equipoLocal;
        this.equipoVisitante=equipoVisitante;
        this.cuentaConArbitro=null;
    }
    //Getters y Setter

    public int getFecha() {
        return fecha;
    }

    public int getHorario() {
        return horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getTiempoAdicional() {
        return tiempoAdicional;
    }

    public Estadio getSeDesarrolla() {
        return seDesarrolla;
    }

    public Fase getCorrespondeFase() {
        return correspondeFase;
    }

    public Arbitro getCuentaConArbitro() {
        return cuentaConArbitro;
    }

    public void asociarArbitro(Arbitro arbitro) {
        this.cuentaConArbitro = arbitro;
    }
    public void asignarArbitraje(Arbitraje arbitraje){
        this.arbitraje=arbitraje;
    }

    public Evento getEvento() {
        return evento;
    }

    public Participacion getEquipoLocal() {
        return equipoLocal;
    }

    public Participacion getEquipoVisitante() {
        return equipoVisitante;
    }

}
