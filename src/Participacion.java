/**
 * Representa la participacion de una seleccion en un partido concreto,
 * indicando si jugo de local o visitante, y permite contar goles y tarjetas
 * de esa seleccion en ese partido.
 */
public class Participacion {
    private boolean esLocal;
    private Seleccion seleccion;
    private Partido partido;

    /**
     * Crea una instancia vacia de {@code Participacion}.
     */
    public Participacion(){
    }

    /**
     * Crea una instancia de {@code Participacion} con los datos indicados.
     *
     * @param esLocal esLocal
     * @param seleccion seleccion
     */
    public Participacion(boolean esLocal, Seleccion seleccion) {
        this.esLocal = esLocal;
        this.seleccion = seleccion;
        this.partido = null;
    }
    //Getters y Setters

    /**
     * Devuelve es local.
     * @return es local
     */
    public boolean isEsLocal() {
        return esLocal;
    }

    /**
     * Establece es local.
     *
     * @param esLocal esLocal
     */
    public void setEsLocal(boolean esLocal) {
        this.esLocal = esLocal;
    }

    /**
     * Devuelve seleccion.
     * @return seleccion
     */
    public Seleccion getSeleccion() {
        return seleccion;
    }

    /**
     * Asocia la seleccion participante.
     *
     * @param seleccion seleccion
     */
    public void asociarSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }


    /**
     * Devuelve partido.
     * @return partido
     */
    public Partido getPartido() {
        return partido;
    }

    /**
     * Asocia el partido en el que se da la participacion.
     *
     * @param partido partido
     */
    public void asociarPartido(Partido partido) {
        this.partido = partido;
    }


    //METODOS

    /**
     * Cuenta los goles de la seleccion en este partido (incluye penales convertidos).
     * @return resultado de la operacion
     */
    public int cantidadGoles() {return contarEventosPorTipo(TipoEvento.GOL)+ contarEventosPorTipo(TipoEvento.PENAL_CONVERTIDO);
    }

    /**
     * Cuenta las tarjetas amarillas de la seleccion en este partido.
     * @return resultado de la operacion
     */
    public int cantidadTarjAmarillas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_AMARILLA)+ contarEventosPorTipo(TipoEvento.DOBLE_AMARILLA);
    }

    /**
     * Cuenta las tarjetas rojas de la seleccion en este partido.
     * @return resultado de la operacion
     */
    public int cantidadTarjRojas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_ROJA)+ contarEventosPorTipo(TipoEvento.DOBLE_AMARILLA);
    }

    private int contarEventosPorTipo(TipoEvento tipoBuscado) {
        if (this.partido == null) return 0;
        int contador = 0;
        for (Evento eventoActual : this.partido.getEventos()) {
            // Filtramos por el tipo de evento que buscamos
            if (eventoActual.getTipo() == tipoBuscado) {

                Jugador jugadorInvolucrado = eventoActual.getJugador();

                // Si el jugador existe, suma
                if (jugadorInvolucrado != null) {
                    Seleccion seleccionDelJugador = jugadorInvolucrado.getSeleccion();

                    //Comparamos las selecciones
                    if (this.seleccion.equals(seleccionDelJugador)) {
                        contador++;
                    }
                }

            }
        }
        return contador;
    }
}
