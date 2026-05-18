public class Participacion {
    private boolean esLocal;
    private Seleccion seleccion;
    private Partido partido;
    public Participacion(boolean esLocal, Seleccion seleccion) {
        this.esLocal = esLocal;
        this.partido = partido;
        this.seleccion = null;
    }
    //Getters y Setters

    public void asociarPartido(Partido partido) {
        this.partido = partido;
    }

    public void setEsLocal(boolean esLocal) {
        this.esLocal = esLocal;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    //Metodos

    public int cantidadGoles(){
        return 0;
    }
    public int cantidadTarjAmarillas(){
        return 0;
    }
    public int cantidadTarjRojas(){
        return 0;
    }
}
