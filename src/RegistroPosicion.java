/**
 * Registro auxiliar que asocia a una seleccion con sus puntos, usado
 * para construir la tabla de posiciones de un grupo.
 */
public class RegistroPosicion {

    private Seleccion seleccion;
    private int puntos;

    /**
    * Crea una instancia de {@code RegistroPosicion} con los datos indicados.
    *
    * @param seleccion seleccion
    * @param puntos puntos
    */
    public RegistroPosicion(Seleccion seleccion, int puntos) {
        this.seleccion = seleccion;
        this.puntos = puntos;
    }

    public Seleccion getSeleccion() { return this.seleccion; }


    public int getPuntos() { return this.puntos; }
}

