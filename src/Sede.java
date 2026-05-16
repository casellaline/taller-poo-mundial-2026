public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Estadio estadio;
    private Pais pais;
    //Constructor
    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria, Estadio estadio, Pais pais) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.estadio = estadio;
        this.pais=pais;
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

    public Estadio getEstadio() {
        return estadio;
    }

    public Pais getPais() {
        return pais;
    }
}
