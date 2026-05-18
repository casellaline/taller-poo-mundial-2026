public class Evento {
    private TipoEvento tipo;
    private int minuto;
    private Jugador involucraJugador;

    //Constructores

    public Evento(TipoEvento tipo, int minuto) {
        this.tipo = tipo;
        this.minuto = minuto;
        this.involucraJugador=null;
    }


    //Getters y Setters

    public void asociarJugador(Jugador jugador) {
        this.involucraJugador = jugador;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public int getMinuto() {
        return minuto;
    }

    public Jugador getInvolucraJugador() {
        return involucraJugador;
    }
}
