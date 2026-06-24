import java.util.ArrayList;
import java.util.List;

public class GestionDelegaciones {
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;
    private List<Arbitro> arbitros;

    // Constructor


    public GestionDelegaciones(List<Pais> paisesRegistrados, List<Seleccion> seleccionesInscriptas, List<Arbitro> arbitros) {
        this.paisesRegistrados = paisesRegistrados;
        this.seleccionesInscriptas = seleccionesInscriptas;
        this.arbitros = arbitros;
    }

    // METODOS CREADORES

    public Pais registrarPais(String nombre, String bandera) {
        Pais nuevoPais = new Pais(nombre, bandera);
        this.paisesRegistrados.add(nuevoPais);
        return nuevoPais;
    }

    public Seleccion registrarSeleccion(String federacion, String camPrin, String camSec,
                                        boolean cabeza, int ranking, Pais pais) {
        Seleccion nuevaSeleccion = new Seleccion(federacion, camPrin, camSec, cabeza, ranking);

        pais.asociarSeleccion(nuevaSeleccion);
        this.seleccionesInscriptas.add(nuevaSeleccion);
        return nuevaSeleccion;
    }

    public void inscribirJugador(String nombre, int anioNac, int dorsal, Posicion posicion,
                                 float peso, float altura, Seleccion seleccionDestino) throws JugadorYaInscriptoException {
        // validación
        for (Seleccion seleccionActual : this.seleccionesInscriptas) {
            if (seleccionActual.getJugadores() != null) {
                for (Jugador jugadorRegistrado : seleccionActual.getJugadores()) {
                    if (jugadorRegistrado.getNombre().equalsIgnoreCase(nombre)) {
                        throw new JugadorYaInscriptoException("El jugador " + nombre + " ya está vinculado a " +
                                seleccionActual.getNombreFederacion());
                    }
                }
            }
        }

        Jugador nuevoJugador = new Jugador(nombre, anioNac, dorsal, posicion, peso, altura, seleccionDestino);
        seleccionDestino.agregarJugador(nuevoJugador);
    }


    public void asignarCuerpoTecnico(String nombre, int anioNac, Rol rol, Seleccion seleccion) {
        CuerpoTecnico integrante = new CuerpoTecnico(nombre, anioNac, rol);
        seleccion.agregarCuerpoTecnico(integrante);
    }

    /**
     * Crea y asigna un director técnico a una selección. La instanciación del
     * objeto queda encapsulada en la gestora.
     *
     * @param nombre nombre del director técnico
     * @param anioNac año de nacimiento
     * @param fechaNombramiento fecha de nombramiento
     * @param seleccion selección que dirige
     */
    public void asignarDirectorTecnico(String nombre, int anioNac, int fechaNombramiento, Seleccion seleccion) {
        DirectorTecnico dt = new DirectorTecnico(nombre, anioNac, fechaNombramiento);
        seleccion.agregarDirectorTecnico(dt);
    }

    /**
     * Crea y registra un árbitro en el sistema, asociándolo a un país. La
     * instanciación del objeto queda encapsulada en la gestora.
     *
     * @param nombre nombre del árbitro
     * @param anioNac año de nacimiento
     * @param aniosExperiencia años de experiencia
     * @param pais país al que pertenece
     * @return el árbitro creado y registrado
     */
    public Arbitro crearArbitro(String nombre, int anioNac, int aniosExperiencia, Pais pais) {
        Arbitro nuevoArbitro = new Arbitro(nombre, anioNac, aniosExperiencia, pais);
        this.registrarArbitro(nuevoArbitro);
        return nuevoArbitro;
    }

    // 3. GETTERS Y SETTERS COMPLETO

    public List<Pais> getPaisesRegistrados() { return this.paisesRegistrados; }
    public void setPaisesRegistrados(List<Pais> paisesRegistrados) { this.paisesRegistrados = paisesRegistrados; }

    public List<Seleccion> getSeleccionesInscriptas() { return this.seleccionesInscriptas; }
    public void setSeleccionesInscriptas(List<Seleccion> seleccionesInscriptas) { this.seleccionesInscriptas = seleccionesInscriptas; }

    public Seleccion buscarSeleccionPorNombre(String nombreBuscado) {
        for (Seleccion seleccion : this.seleccionesInscriptas) {
            if (seleccion.getPais().getNombre().equalsIgnoreCase(nombreBuscado)) {
                return seleccion;
            }
        }
        return null;
    }

    public Jugador buscarJugadorPorNombre(String nombre) {

        for (Seleccion seleccion : this.seleccionesInscriptas) {

            for (Jugador jugador : seleccion.getJugadores()) {

                if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                    return jugador;
                }
            }
        }
        return null;
    }

    /**
     * Busca un árbitro en el sistema por su nombre exacto.
     * @param nombre El nombre del árbitro a buscar.
     * @return El objeto Arbitro si lo encuentra, o null si no existe.
     */
    public Arbitro buscarArbitroPorNombre(String nombre) {

        for (Arbitro arbitro : this.arbitros) {

            if (arbitro.getNombre().equalsIgnoreCase(nombre)) {
                return arbitro;
            }
        }
        return null;
    }

    public void registrarArbitro(Arbitro nuevoArbitro) {
        if (nuevoArbitro != null) {
            this.arbitros.add(nuevoArbitro);
        }
    }

}




