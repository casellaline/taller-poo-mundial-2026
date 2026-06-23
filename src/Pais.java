import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un pais participante u organizador. Agrupa sus sedes,
 * arbitros y selecciones nacionales.
 */
public class Pais {
    private String nombre;
    private String bandera;
    private List<Sede> sedes;
    private List<Arbitro> arbitros;
    private List<Seleccion> selecciones;

    //Constructores

    /**
     * Crea una instancia vacia de {@code Pais}.
     */
    public Pais(){
        this.sedes= new ArrayList<Sede>();
        this.arbitros=new ArrayList<Arbitro>();
        this.selecciones= new ArrayList<Seleccion>();

    }
    /**
     * Crea una instancia de {@code Pais} con los datos indicados.
     *
     * @param nombre nombre
     * @param bandera bandera
     */
    public Pais(String nombre, String bandera){
        this.nombre=nombre;
        this.bandera=bandera;
        this.sedes= new ArrayList<Sede>();
        this.arbitros=new ArrayList<Arbitro>();
        this.selecciones= new ArrayList<Seleccion>();
    }

    //Getters y setters

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
     * Devuelve bandera.
     * @return bandera
     */
    public String getBandera() {
        return bandera;
    }

    /**
     * Establece bandera.
     *
     * @param bandera bandera
     */
    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    /**
     * Devuelve sedes.
     * @return sedes
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * Establece sedes.
     *
     * @param sedes sedes
     */
    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    /**
     * Asocia una sede al pais y establece la relacion inversa.
     *
     * @param sede sede
     */
    public void asociarSede(Sede sede){
        this.sedes.add(sede);
        sede.setPais(this);
    }

    /**
     * Devuelve arbitros.
     * @return arbitros
     */
    public List<Arbitro> getArbitros() {
        return arbitros;
    }

    /**
     * Establece arbitros.
     *
     * @param arbitros arbitros
     */
    public void setArbitros(List<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    /**
     * Agrega un arbitro al pais y establece la relacion inversa.
     *
     * @param arbitro arbitro
     */
    public void agregarArbitro(Arbitro arbitro){
        this.arbitros.add(arbitro);
        arbitro.setCuentaConPais(this);
    }

    /**
     * Devuelve selecciones.
     * @return selecciones
     */
    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    /**
     * Establece selecciones.
     *
     * @param seleccion seleccion
     */
    public void setSelecciones(List<Seleccion> seleccion) {
        this.selecciones = seleccion;
    }

    /**
     * Asocia una seleccion al pais y establece la relacion.
     *
     * @param seleccion seleccion
     */
    public void asociarSeleccion(Seleccion seleccion){
        this.selecciones.add(seleccion);
        seleccion.setPais(this);
    }
}
