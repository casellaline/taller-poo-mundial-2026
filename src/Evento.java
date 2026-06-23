/**
 * Representa un evento ocurrido durante un partido (gol, tarjeta,
 * sustitucion, etc.), indicando el tipo, el minuto y el jugador involucrado.
 */
public class Evento {
    private TipoEvento tipo;
    private int minuto;
    private Jugador jugador; // CORRECCIÓN: El jugador involucrado ahora es atributo obligatorio

    // Constructores
    /**
     * Crea una instancia vacia de {@code Evento}.
     */
    public Evento() {
    }

    /**
     * Crea una instancia de {@code Evento} con los datos indicados.
     *
     * @param tipo tipo
     * @param minuto minuto
     * @param jugador jugador
     */
    public Evento(TipoEvento tipo, int minuto, Jugador jugador) {
        this.tipo = tipo;
        this.minuto = minuto;
        this.jugador = jugador;
    }

    // Getters y Setters

    /**
     * Devuelve tipo.
     * @return tipo
     */
    public TipoEvento getTipo() { return tipo; }

    /**
     * Establece tipo.
     *
     * @param tipo tipo
     */
    public void setTipo(TipoEvento tipo) { this.tipo = tipo; }

    /**
     * Devuelve minuto.
     * @return minuto
     */
    public int getMinuto() { return minuto; }

    /**
     * Establece minuto.
     *
     * @param minuto minuto
     */
    public void setMinuto(int minuto) { this.minuto = minuto; }

    /**
     * Devuelve jugador.
     * @return jugador
     */
    public Jugador getJugador() { return jugador; }

    /**
     * Establece jugador.
     *
     * @param jugador jugador
     */
    public void setJugador(Jugador jugador) { this.jugador = jugador; }
}