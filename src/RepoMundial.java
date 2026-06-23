import java.util.ArrayList;
import java.util.List;
/**
 * Repositorio central en memoria que actua como unica fuente de datos
 * del sistema. Mantiene las listas de paises, selecciones, sedes, partidos,
 * grupos y arbitros que comparten las gestoras.
 */
public class RepoMundial {
    private List<Pais> paises;
    private List<Seleccion> selecciones;
    private List<Sede> sedes;
    private List<Partido> partidos;
    private List<Grupo> gruposMundial;
    private List<Arbitro> arbitros;

    /**
     * Crea una instancia vacia de {@code RepoMundial}.
     */
    public RepoMundial() {
        this.paises = new ArrayList<>();
        this.selecciones = new ArrayList<>();
        this.sedes = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.gruposMundial = new ArrayList<>();
        this.arbitros= new ArrayList<>();
    }

    //Getters

    /**
     * Devuelve paises.
     * @return paises
     */
    public List<Pais> getPaises() {
        return paises;
    }

    /**
     * Devuelve selecciones.
     * @return selecciones
     */
    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    /**
     * Devuelve sedes.
     * @return sedes
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * Devuelve partidos.
     * @return partidos
     */
    public List<Partido> getPartidos() {
        return partidos;
    }

    /**
     * Devuelve grupos mundial.
     * @return grupos mundial
     */
    public List<Grupo> getGruposMundial() {
        return gruposMundial;
    }

    /**
     * Devuelve arbitros.
     * @return arbitros
     */
    public List<Arbitro> getArbitros(){
        return arbitros;
    }
}
