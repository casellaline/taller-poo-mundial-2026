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

    public void setPartido(Partido partido) {
        this.partido = partido;
    }



    public int cantidadGoles() {return contarEventosPorTipo(TipoEvento.GOL)+ contarEventosPorTipo(TipoEvento.PENAL_CONVERTIDO);
    }

    public int cantidadTarjAmarillas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_AMARILLA)+ contarEventosPorTipo(TipoEvento.DOBLE_AMARILLA);
    }

    public int cantidadTarjRojas() {
        return contarEventosPorTipo(TipoEvento.TARJETA_ROJA)+ contarEventosPorTipo(TipoEvento.DOBLE_AMARILLA);
    }

    private int contarEventosPorTipo(TipoEvento tipoBuscado) {
        if (this.partido == null) return 0;
        int contador = 0;
        for (Evento eventoActual : this.partido.getEventos()) {
            // Filtramos por el tipo de evento que buscamos
            if (eventoActual.getTipo() == tipoBuscado) {

                Jugador jugadorInvolucrado = eventoActual.getInvolucraJugador();

                // Si el jugador existe (no es null), entramos a sumar
                if (jugadorInvolucrado == null) {
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
