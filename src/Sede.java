import java.util.ArrayList;
import java.util.List;

public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Pais pertenecePais;
    private List<Estadio> estadios;

    //Constructor

    public Sede(){
        this.estadios=new ArrayList<Estadio>();
    }

    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria,
                Pais pertenecePais) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.pertenecePais=pertenecePais;
        this.estadios = new ArrayList<Estadio>();
    }
    //Getters & Setters

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

    public Pais getPertenecePais() {
        return pertenecePais;
    }

    public void setPertenecePais(Pais pertenecePais) {
        this.pertenecePais = pertenecePais;
    }

    public List<Estadio> getEstadios() {
        return estadios;
    }

    public void setEstadios(List<Estadio> estadios) {
        this.estadios = estadios;
    }

    public void agregarEstadio(Estadio estadio){
        this.estadios.add(estadio);
    }
}
