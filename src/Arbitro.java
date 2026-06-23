import java.util.ArrayList;
import java.util.List;
/**
 * Representa a un arbitro del torneo, con sus anios de experiencia, el
 * pais al que pertenece y los arbitrajes que tiene asignados.
 */
public class Arbitro extends Persona{
    private int aniosExperiencia;
    private Pais cuentaConPais;
    private List<Arbitraje> arbitrajes;

    //Constructores

    /**
     * Crea una instancia vacia de {@code Arbitro}.
     */
    public Arbitro(){
        super(); // Llama al constructor vacío de Persona
        this.arbitrajes = new ArrayList<Arbitraje>();
    }

    /**
     * Crea una instancia de {@code Arbitro} con los datos indicados.
     *
     * @param nombre nombre
     * @param fecNacimiento fecNacimiento
     * @param aniosExperiencia aniosExperiencia
     * @param cuentaConPais cuentaConPais
     */
    public Arbitro(String nombre, int fecNacimiento, int aniosExperiencia,
                   Pais cuentaConPais){
        super(nombre, fecNacimiento);
        this.aniosExperiencia=aniosExperiencia;
        this.cuentaConPais=cuentaConPais;
        this.arbitrajes = new ArrayList<>();//Se crea una lista vacia por la herencia
    }

    //Getters y Setters

    /**
     * Devuelve anios experiencia.
     * @return anios experiencia
     */
    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    /**
     * Establece anios experiencia.
     *
     * @param aniosExperiencia aniosExperiencia
     */
    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    /**
     * Devuelve cuenta con pais.
     * @return cuenta con pais
     */
    public Pais getCuentaConPais() {
        return cuentaConPais;
    }

    /**
     * Establece cuenta con pais.
     *
     * @param cuentaConPais cuentaConPais
     */
    public void setCuentaConPais(Pais cuentaConPais) {
        this.cuentaConPais = cuentaConPais;
    }

    /**
     * Devuelve arbitrajes.
     * @return arbitrajes
     */
    public List<Arbitraje> getArbitrajes() {
        return arbitrajes;
    }

    /**
     * Establece arbitrajes.
     *
     * @param arbitrajes arbitrajes
     */
    public void setArbitrajes(List<Arbitraje> arbitrajes) {
        this.arbitrajes = arbitrajes;
    }

    /**
     * Agrega un arbitraje al arbitro y establece la relacion inversa.
     *
     * @param arbitraje arbitraje
     */
    public void agregarArbitraje(Arbitraje arbitraje){
        this.arbitrajes.add(arbitraje);
        arbitraje.setArbitro(this);
    }
}
