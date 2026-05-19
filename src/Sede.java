import java.util.ArrayList;
import java.util.List;

public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Pais pertenecePais;
    private List<Estadio> tieneEstadio;
    //Constructor
    public Sede(){
        this.tieneEstadio=new ArrayList<Estadio>();
    }
    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria, Pais pertenecePais, List<Estadio> tieneEstadio) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.pertenecePais=pertenecePais;
        this.tieneEstadio = tieneEstadio;
    }
    //Getters
    public String getCiudad() {
        return ciudad;
    }

    public float getAlturaNivelMar() {
        return alturaNivelMar;
    }

    public String getClima() {
        return clima;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    //public Estadio getEstadio() {return tieneEstadio;    }

    public Pais getPais() {
        return pertenecePais;
    }
    public void agregarEstadio(Estadio estadio){
        this.tieneEstadio.add(estadio);
    }
}
