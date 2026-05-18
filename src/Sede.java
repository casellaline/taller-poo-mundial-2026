public class Sede {
    private String ciudad;
    private float alturaNivelMar;
    private String clima;
    private String zonaHoraria;
    private Estadio tieneEstadio;
    private Pais pertenecePais;
    //Constructor
    public Sede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria, Estadio tieneEstadio, Pais pertenecePais) {
        this.ciudad = ciudad;
        this.alturaNivelMar = alturaNivelMar;
        this.clima = clima;
        this.zonaHoraria = zonaHoraria;
        this.tieneEstadio = tieneEstadio;
        this.pertenecePais=pertenecePais;
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
        return tieneEstadio;
    }

    public Pais getPais() {
        return pertenecePais;
    }
}
