/**
 * Clase base abstracta para las personas del sistema (jugadores,
 * arbitros, directores tecnicos y cuerpo tecnico). Centraliza los datos
 * comunes de nombre y fecha de nacimiento.
 */
public abstract class Persona {
    private String nombre;
    private int fecNacimiento;

    //Constructor

    /**
     * Crea una instancia vacia de {@code Persona}.
     */
    public Persona(){}

    /**
     * Crea una instancia de {@code Persona} con los datos indicados.
     *
     * @param nombre nombre
     * @param fecNacimiento fecNacimiento
     */
    public Persona(String nombre, int fecNacimiento){
        this.nombre=nombre;
        this.fecNacimiento=fecNacimiento;
    }
    //getters y setters

    /**
     * Devuelve nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve fec nacimiento.
     * @return fec nacimiento
     */
    public int getFecNacimiento() {
        return fecNacimiento;
    }

    /**
     * Establece fec nacimiento.
     *
     * @param fecNacimiento fecNacimiento
     */
    public void setFecNacimiento(int fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }
}
