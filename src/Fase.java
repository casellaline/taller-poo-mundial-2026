import java.util.ArrayList;
import java.util.List;
/**
 * Representa una fase del torneo (grupos, octavos, cuartos, etc.) y agrupa
 * los partidos y grupos que le corresponden.
 */
public class Fase {
    private NombreFase nombre;
    private List<Partido> partidos;
    private List<Grupo> grupos;

    //Constructores

    /**
     * Crea una instancia vacia de {@code Fase}.
     */
    public Fase(){
        this.partidos= new ArrayList<Partido>();
        this.grupos= new ArrayList<Grupo>();
    }

    /**
     * Crea una instancia de {@code Fase} con los datos indicados.
     *
     * @param nombre nombre
     */
    public Fase(NombreFase nombre) {
        this.nombre = nombre;
        this.partidos= new ArrayList<Partido>();
        this.grupos= new ArrayList<Grupo>();
    }
    //Getters y setters

    /**
     * Devuelve nombre.
     * @return nombre
     */
    public NombreFase getNombre() {
        return nombre;
    }

    /**
     * Establece nombre.
     *
     * @param nombre nombre
     */
    public void setNombre(NombreFase nombre) {
        this.nombre = nombre;
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
     * Devuelve grupos.
     * @return grupos
     */
    public List<Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Establece grupos.
     *
     * @param grupos grupos
     */
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * Asocia un partido a la fase y establece la relacion inversa.
     *
     * @param partido partido
     */
    public void asociarPartido(Partido partido){
        this.partidos.add(partido);
        partido.setCorrespondeFase(this);
    }

    /**
     * Asocia un grupo a la fase y establece la relacion inversa.
     *
     * @param grupo grupo
     */
    public void asociarGrupo(Grupo grupo) {
        this.grupos.add(grupo);
        grupo.setIncluyeFase(this);
    }

    /**
     * Dos fases se consideran equivalentes si tienen el mismo nombre
     * (la misma constante del enum {@code NombreFase}). Esto permite comparar
     * correctamente instancias distintas que representan la misma fase del
     * torneo.
     *
     * @param obj objeto a comparar
     * @return {@code true} si ambas fases tienen el mismo nombre
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Fase otra = (Fase) obj;
        return this.nombre == otra.nombre;
    }

    /**
     * Calcula el hash en base al nombre de la fase, de forma coherente con
     * {@link #equals(Object)}.
     *
     * @return codigo hash de la fase
     */
    @Override
    public int hashCode() {
        return (this.nombre == null) ? 0 : this.nombre.hashCode();
    }
}
