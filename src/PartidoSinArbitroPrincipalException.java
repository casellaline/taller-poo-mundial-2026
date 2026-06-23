/**
 * Excepcion lanzada cuando un partido no tiene asignado un arbitro con
 * el rol principal.
 */
public class PartidoSinArbitroPrincipalException extends Exception {
    /**
     * Crea una instancia de {@code PartidoSinArbitroPrincipalException} con los datos indicados.
     *
     * @param message message
     */
    public PartidoSinArbitroPrincipalException(String message) {
        super(message);
    }
}
