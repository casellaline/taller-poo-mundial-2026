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
    private List<Evento> involucraEvento;
    //Constructores
    public Partido (){
        this.arbitrajes=new ArrayList<Arbitraje>();
        this.involucraEvento=new ArrayList<Evento>();
    }

    public Partido(int fecha, int horario, int duracion, int tiempoAdicional, Estadio seDesarrolla, Fase correspondeFase, Participacion equipoLocal, Participacion equipoVisitante ) {
        this.fecha = fecha;
        this.horario=horario;
        this.duracion=duracion;
        this.tiempoAdicional=tiempoAdicional;
        this.seDesarrolla=seDesarrolla;
        this.correspondeFase=correspondeFase;
        this.equipoLocal=equipoLocal;
        this.equipoVisitante=equipoVisitante;
        this.involucraEvento=new ArrayList<Evento>();
    }
    //Getters y Setter
    public void setArbitrajes(List<Arbitraje> arbitrajes){
        this.arbitrajes=arbitrajes;
    }
    public void agregarArbitraje(Arbitraje arbi){
        this.arbitrajes.add(arbi);
    }
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

    //public Evento getEvento() {return involucraEvento;}

    public Participacion getEquipoLocal() {
        return equipoLocal;
    }

    public Participacion getEquipoVisitante() {
        return equipoVisitante;
    }

    //Metodos

    public void agregarEvento(TipoEvento tipo, int minuto){
        this.involucraEvento.add( new Evento (tipo, minuto));
    }
}
