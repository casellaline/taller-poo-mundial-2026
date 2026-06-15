public class Participacion {
    private boolean esLocal;
    private Seleccion seleccion;
    private Partido partido;

    public Participacion(){
    }

    public Participacion(boolean esLocal, Seleccion seleccion) {
        this.esLocal = esLocal;
        this.seleccion = seleccion;
        this.partido = null;
    }
    //Getters y Setters

    public boolean isEsLocal() {
        return esLocal;
    }

    public void setEsLocal(boolean esLocal) {
        this.esLocal = esLocal;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void asociarSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public Partido getPartido() {
        return partido;
    }

    public void asociarPartido(Partido partido) {
        this.partido = partido;
    }


    //Metodos
    public int cantidadGoles() {
        return contarEventosPorTipo(TipoEvento.GOL);
    }

    public int cantidadTarjAmarillas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_AMARILLA);
    }

    public int cantidadTarjRojas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_ROJA);
    }

    private int contarEventosPorTipo(TipoEvento tipoBuscado) {
        int contador = 0;

        // Recorremos la lista de eventos asumiendo que partido y lista existen
        for (Evento eventoActual : this.partido.getEventos()) {

            if (eventoActual.getTipo() == tipoBuscado) {
                Jugador jugadorInvolucrado = eventoActual.getInvolucraJugador();
                Seleccion seleccionDelJugador = jugadorInvolucrado.getSeleccion();

                // Verificamos si el evento pertenece a la selección de esta participación
                if (this.seleccion.equals(seleccionDelJugador)) {
                    contador++;
                }
            }
        }

        return contador;
    }
}
