/**
 * Excepcion lanzada al intentar inscribir un jugador que ya esta
 * vinculado a una seleccion nacional.
 */
public class JugadorYaInscriptoException extends Exception   {
    /**
     * Crea una instancia de {@code JugadorYaInscriptoException} con los datos indicados.
     *
     * @param message message
     */
    public JugadorYaInscriptoException(String message) {
        super(message);
    }
}
