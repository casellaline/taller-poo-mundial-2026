import java.util.ArrayList;
import java.util.List;
/**
 * Representa un estadio donde se disputan partidos, con su nombre,
 * capacidad, la sede a la que pertenece y los partidos albergados.
 */
public class Estadio {
    private String nombre;
    private int capacidad;
    private Sede sede;
    private List<Partido> partidos;

    //Constructores

    /**
     * Crea una instancia vacia de {@code Estadio}.
     */
    public Estadio(){
        this.partidos= new ArrayList<Partido>();
    }

    /**
     * Crea una instancia de {@code Estadio} con los datos indicados.
     *
     * @param nombre nombre
     * @param capacidad capacidad
     * @param sede sede
     */
    public Estadio(String nombre, int capacidad, Sede sede){
        this.nombre=nombre;
        this.capacidad=capacidad;
        this.sede=sede;
        this.partidos= new ArrayList<Partido>();
    }
    //Getters & Setters

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
     * Devuelve capacidad.
     * @return capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece capacidad.
     *
     * @param capacidad capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Devuelve sede.
     * @return sede
     */
    public Sede getSede() {
        return sede;
    }

    /**
     * Establece sede.
     *
     * @param sede sede
     */
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    /**
     * Devuelve partidos.
     * @return partidos
     */
    public List<Partido> getPartidos() {
        return partidos;
    }

    /**
     * Establece partidos.
     *
     * @param partidos partidos
     */
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    /**
     * Agrega un partido a la lista de partidos del estadio.
     *
     * @param partido partido
     */
    public void agregarPartido(Partido partido) {
        this.partidos.add(partido);
    }

}

