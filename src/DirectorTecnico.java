/**
 * Representa al director tecnico de una seleccion, con su fecha de
 * nombramiento y la seleccion que dirige.
 */
public class DirectorTecnico extends Persona{
    private int fechaNombramiento;
    private Seleccion seleccion;

    //Constructor

    /**
     * Crea una instancia vacia de {@code DirectorTecnico}.
     */
    public DirectorTecnico(){}

    /**
     * Crea una instancia de {@code DirectorTecnico} con los datos indicados.
     *
     * @param nombre nombre
     * @param fecNacimiento fecNacimiento
     * @param fechaNombramiento fechaNombramiento
     */
    public DirectorTecnico(String nombre, int fecNacimiento, int fechaNombramiento){
        super(nombre, fecNacimiento);
        this.fechaNombramiento=fechaNombramiento;
    }

    //Getters y Setters
    /**
     * Devuelve fecha nombramiento.
     * @return fecha nombramiento
     */
    public int getFechaNombramiento() {
        return fechaNombramiento;
    }

    /**
     * Establece fecha nombramiento.
     *
     * @param fechaNombramiento fechaNombramiento
     */
    public void setFechaNombramiento(int fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    /**
     * Devuelve seleccion.
     * @return seleccion
     */
    public Seleccion getSeleccion() {
        return seleccion;
    }

    /**
     * Establece seleccion.
     *
     * @param seleccion seleccion
     */
    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }
}