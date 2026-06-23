/**
 * Representa a un integrante del cuerpo tecnico de una seleccion
 * (por ejemplo medico o preparador fisico), identificado por su rol.
 */
public class CuerpoTecnico extends Persona{
    private Rol rol;
    private Seleccion seleccion;

    //Constructor

    /**
     * Crea una instancia vacia de {@code CuerpoTecnico}.
     */
    public CuerpoTecnico(){}

    /**
     * Crea una instancia de {@code CuerpoTecnico} con los datos indicados.
     *
     * @param nombre nombre
     * @param fecNacimiento fecNacimiento
     * @param rol rol
     */
    public CuerpoTecnico(String nombre, int fecNacimiento, Rol rol){
        super(nombre, fecNacimiento);
        this.rol=rol;
    }
    //Getters & Setters

    /**
     * Devuelve rol.
     * @return rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece rol.
     *
     * @param rol rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Devuelve seleccion.
     * @return seleccion
     */
    public Seleccion getSeleccion(){
        return seleccion;
    }

    /**
     * Establece seleccion.
     *
     * @param seleccion seleccion
     */
    public void setSeleccion(Seleccion seleccion){
        this.seleccion=seleccion;
    }
}
