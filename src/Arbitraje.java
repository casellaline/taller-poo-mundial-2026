public class Arbitraje {
    private CategoriaArbitro rol;
    private Arbitro arbitro;
    private Partido partido;
    //constructores
    public Arbitraje(){
    }
    public Arbitraje(CategoriaArbitro rol, Arbitro arbitro, Partido partido){
        this.rol=rol;
        this.arbitro=arbitro;
        this.partido=partido;
    }
    //Getters & Setters

    public CategoriaArbitro getRol() {
        return rol;
    }

    public void setRol(CategoriaArbitro rol) {
        this.rol = rol;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
