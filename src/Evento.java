public class Evento {
    private TipoEvento tipo;
    private int minuto;
    private Jugador jugador; // CORRECCIÓN: El jugador involucrado ahora es atributo obligatorio

    // 1. Constructor vacío
    public Evento() {
    }

    // 2. Constructor parametrizado (Recibe TODO lo que involucra)
    public Evento(TipoEvento tipo, int minuto, Jugador jugador) {
        this.tipo = tipo;
        this.minuto = minuto;
        this.jugador = jugador;
    }

    // Getters y Setters
    public TipoEvento getTipo() { return tipo; }
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }

    public int getMinuto() { return minuto; }
    public void setMinuto(int minuto) { this.minuto = minuto; }

    public Jugador getJugador() { return jugador; }
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}