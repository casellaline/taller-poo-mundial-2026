/**
 * Tipos de evento que pueden registrarse durante un partido.
 */
public enum TipoEvento {
    /** Gol convertido. */
    GOL,
    /** Tarjeta amarilla. */
    TARJETA_AMARILLA,
    /** Tarjeta roja. */
    TARJETA_ROJA,
    /** Penal cometido. */
    PENAL_COMETIDO,
    /** Penal convertido. */
    PENAL_CONVERTIDO,
    /** Penal errado. */
    PENAL_ERRADO,
    /** Doble amarilla (equivale a expulsion). */
    DOBLE_AMARILLA,
    /** Sustitucion de un jugador. */
    SUSTITUCION,
    /** Lesion de un jugador. */
    LESION
}
