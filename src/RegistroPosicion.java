public class RegistroPosicion {

    private Seleccion seleccion;
    private int puntos;


    public RegistroPosicion(Seleccion seleccion, int puntos) {
        this.seleccion = seleccion;
        this.puntos = puntos;
    }

    /** @return la selección de la fila */
    public Seleccion getSeleccion() { return this.seleccion; }

    /** @return los puntos acumulados */
    public int getPuntos() { return this.puntos; }
}

