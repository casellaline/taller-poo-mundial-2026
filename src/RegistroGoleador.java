/**
 * Registro auxiliar que asocia a un jugador con la cantidad de goles
 * convertidos, usado para construir el ranking de goleadores.
 */
public class RegistroGoleador {

    private Jugador jugador;
    private int goles;

    /**
     * Crea una instancia de {@code RegistroGoleador} con los datos indicados.
     *
     * @param jugador jugador
     * @param goles goles
     */
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
    /**
     * @return el jugador asociado a este registro.
     */


    public Jugador getJugador() { return this.jugador; }
    /**
     * @return la cantidad total de goles anotados por el jugador.
     */

    public int getGoles() { return this.goles; }

}
