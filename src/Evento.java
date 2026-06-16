public class Evento {
    private TipoEvento tipo;
    private int minuto;
    private Jugador jugador;

    // Constructores

    public Evento() {
    }

    public Evento(TipoEvento tipo, int minuto) {
        this.tipo = tipo;
        this.minuto = minuto;
    }

    // Getters y Setters

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public Jugador getJugador() {
        return jugador;
    }

    // Único setter puro para evitar la recursión (bucle infinito)
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}