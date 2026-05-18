public class Arbitraje {
    private CategoriaArbitro rol;
    private Arbitro arbitro;
    //constructores
    public Arbitraje(CategoriaArbitro rol, Arbitro arbitro){
        this.rol=rol;
        this.arbitro=arbitro;
    }
    //Getters

    public CategoriaArbitro getRol() {
        return rol;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }
}
