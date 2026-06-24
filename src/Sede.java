import java.util.ArrayList;
import java.util.List;

/**
 * Representa una sede (ciudad) del Mundial, con sus datos geograficos y
 * climaticos y los estadios que contiene.
 */
public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Pais pais; // Corregido para que sea un sustantivo claro
    private List<Estadio> estadios;

    // Constructore

    /**
     * Crea una instancia vacia de {@code Sede}.
     */
    public Sede() {
        this.estadios = new ArrayList<Estadio>();
    }

    /**
     * Crea una instancia de {@code Sede} con los datos indicados.
     *
     * @param ciudad ciudad
     * @param alturaNivelMar alturaNivelMar
     * @param clima clima
     * @param zonaHoraria zonaHoraria
     */
    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.estadios = new ArrayList<Estadio>();
    }

    // Getters & Setters

    /**
     * Devuelve ciudad.
     * @return ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Establece ciudad.
     *
     * @param ciudad ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Devuelve altura nivel mar.
     * @return altura nivel mar
     */
    public float getAlturaNivelMar() {
        return alturaNivelMar;
    }

    /**
     * Establece altura nivel mar.
     *
     * @param alturaNivelMar alturaNivelMar
     */
    public void setAlturaNivelMar(float alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

    /**
     * Devuelve clima.
     * @return clima
     */
    public String getClima() {
        return clima;
    }

    /**
     * Establece clima.
     *
     * @param clima clima
     */
    public void setClima(String clima) {
        this.clima = clima;
    }

    /**
     * Devuelve zona horaria.
     * @return zona horaria
     */
    public String getZonaHoraria() {
        return zonaHoraria;
    }

    /**
     * Establece zona horaria.
     *
     * @param zonaHoraria zonaHoraria
     */
    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    /**
     * Devuelve pais.
     * @return pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * Establece pais.
     *
     * @param pais pais
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * Devuelve estadios.
     * @return estadios
     */
    public List<Estadio> getEstadios() {
        return estadios;
    }

    /**
     * Establece estadios.
     *
     * @param estadios estadios
     */
    public void setEstadios(List<Estadio> estadios) {
        this.estadios = estadios;
    }

    /**
     * Agrega un estadio a la sede y establece la relacion inversa.
     *
     * @param estadio estadio
     */
    public void agregarEstadio(Estadio estadio) {
        this.estadios.add(estadio);
        estadio.setSede(this);
    }
}