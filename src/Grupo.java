import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    private List<Seleccion> selecciones;
    //Constructor

    public Grupo(){
        this.selecciones=new ArrayList<Seleccion>();
    }

    public Grupo(String identificacion, String descripcion, Fase incluyeFase) {
        this.identificacion = identificacion;
        this.descripcion = descripcion;
        this.incluyeFase = incluyeFase;
        this.selecciones = new ArrayList<Seleccion>();
    }
    //Getter

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Fase getIncluyeFase() {
        return incluyeFase;
    }

    public void setIncluyeFase(Fase incluyeFase) {
        this.incluyeFase = incluyeFase;
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    public void setSelecciones(List<Seleccion> selecciones) {
        this.selecciones = selecciones;
    }

    public void asociarSeleccion(Seleccion seleccion){
        this.selecciones.add(seleccion);
        seleccion.setGrupo(this);
    }
    //Metodos

    public int obtenerPuntos(Seleccion s){
        return 0;
    }
}
