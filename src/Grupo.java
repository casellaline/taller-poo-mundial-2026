public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    private Seleccion seAgrupaSeleccion;
    //Constructor

    public Grupo(String identificacion, String descripcion, Fase incluyeFase, Seleccion seAgrupaSeleccion) {
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

    public Seleccion getSeAgrupaSeleccion() {
        return seAgrupaSeleccion;
    }
    //Metodos

    public int obtenerPuntos(Seleccion s){
        return 0;
    }
}
