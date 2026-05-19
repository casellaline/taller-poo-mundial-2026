import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    private List<Seleccion> seAgrupaSeleccion;
    //Constructor
    public Grupo(){
        this.seAgrupaSeleccion=new ArrayList<Seleccion>();
    }
    public Grupo(String identificacion, String descripcion, Fase incluyeFase, List<Seleccion> seAgrupaSeleccion) {
        this.identificacion = identificacion;
        this.descripcion = descripcion;
        this.incluyeFase = incluyeFase;
        this.seAgrupaSeleccion = seAgrupaSeleccion;
    }
    //Getter

    public String getIdentificacion() {
        return identificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Fase getIncluyeFase() {
        return incluyeFase;
    }
    public void asociarSeleccion(Seleccion seleccion){
        this.seAgrupaSeleccion.add(seleccion);
    }
    //Metodos

    public int obtenerPuntos(Seleccion s){
        return 0;
    }
}
