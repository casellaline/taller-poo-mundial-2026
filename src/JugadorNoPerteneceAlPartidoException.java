/**
 * Excepcion lanzada al intentar registrar un evento sobre un jugador
 * que no participa en el partido indicado.
 */
public class JugadorNoPerteneceAlPartidoException extends Exception {

    /**
     * Crea una instancia de {@code JugadorNoPerteneceAlPartidoException} con los datos indicados.
     *
     * @param message message
     */
    public JugadorNoPerteneceAlPartidoException(String message) {
        super(message);
    }
}
