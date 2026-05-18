public class Arbitraje {
    private CategoriaArbitro rol;
    private Arbitro arbitro;
    private Partido partido;
    //constructores
    public Arbitraje(CategoriaArbitro rol, Arbitro arbitro, Partido partido){
        this.rol=rol;
        this.arbitro=arbitro;
        this.partido=partido;
    }
    //Getters

    public CategoriaArbitro getRol() {
        return rol;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public Partido getPartido() {
        return partido;
    }
}
