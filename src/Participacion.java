public class Participacion {
    private boolean esLocal;
    private Seleccion seleccion;
    private Partido partido;
    public Participacion(boolean esLocal, Seleccion seleccion) {
        this.esLocal = esLocal;
        this.seleccion = seleccion;
        this.partido = null;
    }
    //Getters y Setters

    public void asociarPartido(Partido partido) {
        this.partido = partido;
    }

    public boolean isEsLocal() {
        return esLocal;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public Partido getPartido() {
        return partido;
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
