public class RegistroGoleador {

    private Jugador jugador;
    private int goles;

    public RegistroGoleador(Jugador jugador, int goles) {
        this.jugador = jugador;
        this.goles = goles;
    }

    /**
     * Incrementa en uno la cantidad de goles del jugador.
     */
    public void sumarGol() {
        this.goles = this.goles + 1;
    }

    /** @return el jugador de la fila */
    public Jugador getJugador() { return this.jugador; }

    /** @return la cantidad de goles acumulados */
    public int getGoles() { return this.goles; }

}
