import java.util.ArrayList;
import java.util.List;

public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Pais pais; // Corregido para que sea un sustantivo claro
    private List<Estadio> estadios;

    // Constructor vacío
    public Sede() {
        this.estadios = new ArrayList<Estadio>();
    }

    // Constructor parametrizado (Sin el País, para obligar a usar la asociación segura)
    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.estadios = new ArrayList<Estadio>();
    }

    // Getters & Setters

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public float getAlturaNivelMar() {
        return alturaNivelMar;
    }

    public void setAlturaNivelMar(float alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public Pais getPais() {
        return pais;
    }

    // Este setter lo usa la clase Pais en su método asociarSede
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Estadio> getEstadios() {
        return estadios;
    }

    public void setEstadios(List<Estadio> estadios) {
        this.estadios = estadios;
    }

    // Excelente manejo bidireccional
    public void agregarEstadio(Estadio estadio) {
        this.estadios.add(estadio);
        estadio.setSede(this);
    }
}