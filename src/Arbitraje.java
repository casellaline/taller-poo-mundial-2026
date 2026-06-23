/**
 * Representa la asignacion de un arbitro a un partido con una categoria
 * determinada (principal, asistente, VAR, etc.).
 */
public class Arbitraje {
    private CategoriaArbitro rol;
    private Arbitro arbitro;
    private Partido partido;

    //constructores
    /**
     * Crea una instancia vacia de {@code Arbitraje}.
     */
    public Arbitraje(){}

    /**
     * Crea una instancia de {@code Arbitraje} con los datos indicados.
     *
     * @param rol rol
     * @param arbitro arbitro
     * @param partido partido
     */
    public Arbitraje(CategoriaArbitro rol, Arbitro arbitro, Partido partido){
        this.rol=rol;
        this.arbitro=arbitro;
        this.partido=partido;
    }
    //Getters & Setters

    /**
     * Devuelve rol.
     * @return rol
     */
    public CategoriaArbitro getRol() {
        return rol;
    }

    /**
     * Establece rol.
     *
     * @param rol rol
     */
    public void setRol(CategoriaArbitro rol) {
        this.rol = rol;
    }

    /**
     * Devuelve arbitro.
     * @return arbitro
     */
    public Arbitro getArbitro() {
        return arbitro;
    }

    /**
     * Establece arbitro.
     *
     * @param arbitro arbitro
     */
    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    /**
     * Devuelve partido.
     * @return partido
     */
    public Partido getPartido() {
        return partido;
    }

    /**
     * Establece partido.
     *
     * @param partido partido
     */
    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
