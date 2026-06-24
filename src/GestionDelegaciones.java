import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la administracion de delegaciones: registro de
 * paises y selecciones, inscripcion de jugadores y cuerpo tecnico, registro
 * de arbitros y busquedas de selecciones, jugadores y arbitros.
 */
public class GestionDelegaciones {
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;
    private List<Arbitro> arbitros;

    // Constructor

    /**
     * Crea una instancia de {@code GestionDelegaciones} con los datos indicados.
     *
     * @param paisesRegistrados paisesRegistrados
     * @param seleccionesInscriptas seleccionesInscriptas
     * @param arbitros arbitros
     */
    public GestionDelegaciones(List<Pais> paisesRegistrados, List<Seleccion> seleccionesInscriptas, List<Arbitro> arbitros) {
        this.paisesRegistrados = paisesRegistrados;
        this.seleccionesInscriptas = seleccionesInscriptas;
        this.arbitros = arbitros;
    }

    // METODOS CREADORES

    /**
     * Registra un nuevo pais en el sistema.
     *
     * @param nombre nombre del pais
     * @param bandera bandera (descripción)
     * @return resultado de la operacion
     */
    public Pais registrarPais(String nombre, String bandera) {
        Pais nuevoPais = new Pais(nombre, bandera);
        this.paisesRegistrados.add(nuevoPais);
        return nuevoPais;
    }

    /**
     * Registra una nueva selección nacional en el sistema, instanciando el objeto,
     * estableciendo la relación con su y añadiéndola a las selecciones inscriptas de la gestora.
     *      *
     * @param federacion nombre de la federación de fútbol
     * @param camPrin    descripción o color de la indumentaria o camiseta principal
     * @param camSec     descripción o color de la indumentaria o camiseta secundaria
     * @param cabeza     {@code true} si la selección es cabeza de grupo en el torneo
     * @param ranking    posición actual de la selección en el ranking oficial de la FIFA
     * @param pais       objeto {@link Pais} al cual representa y se asocia la selección
     * @return la instancia de {@link Seleccion} creada y registrada con éxito
     */
    public Seleccion registrarSeleccion(String federacion, String camPrin, String camSec,
                                        boolean cabeza, int ranking, Pais pais) {
        Seleccion nuevaSeleccion = new Seleccion(federacion, camPrin, camSec, cabeza, ranking);

        pais.asociarSeleccion(nuevaSeleccion);
        this.seleccionesInscriptas.add(nuevaSeleccion);
        return nuevaSeleccion;
    }

    /**
     * Inscribe un nuevo jugador en el plantel de una selección específica.
     * El método ejecuta una validación recorriendo todas las selecciones registradas,
     * impidiendo inscripciones duplicadas mediante
     *
     * @param nombre           nombre completo del jugador a inscribir
     * @param anioNac          año de nacimiento (utilizado para calcular la edad del deportista)
     * @param dorsal           número de camiseta asignado al jugador en el plantel
     * @param posicion         demarcación táctica en el campo de juego mediante el enum {@link Posicion}
     * @param peso             peso corporal en kilogramos (float)
     * @param altura           estatura en metros (float)
     * @param seleccionDestino la instancia de {@link Seleccion} a la que se integrará el jugador
     * @throws JugadorYaInscriptoException si ya existe un jugador registrado en el torneo
     * con el mismo nombre (violación de regla de negocio)
     */
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

    /**
     * Crea y asigna un integrante del cuerpo tecnico a una seleccion.
     *
     * @param nombre nombre
     * @param anioNac año de Nacimiento
     * @param rol rol que ejerce
     * @param seleccion seleccion que pertenece
     */
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

    /**
     * Devuelve paises registrados.
     * @return paises registrados
     */
    public List<Pais> getPaisesRegistrados() { return this.paisesRegistrados; }

    /**
     * Establece paises registrados.
     *
     * @param paisesRegistrados paisesRegistrados
     */
    public void setPaisesRegistrados(List<Pais> paisesRegistrados) { this.paisesRegistrados = paisesRegistrados; }

    /**
     * Devuelve selecciones inscriptas.
     * @return selecciones inscriptas
     */
    public List<Seleccion> getSeleccionesInscriptas() { return this.seleccionesInscriptas; }

    /**
     * Establece selecciones inscriptas.
     *
     * @param seleccionesInscriptas seleccionesInscriptas
     */
    public void setSeleccionesInscriptas(List<Seleccion> seleccionesInscriptas) { this.seleccionesInscriptas = seleccionesInscriptas; }

    /**
     * Busca una seleccion por el nombre de su pais.
     *
     * @param nombreBuscado nombreBuscado
     * @return resultado de la operacion
     */
    public Seleccion buscarSeleccionPorNombre(String nombreBuscado) {
        for (Seleccion seleccion : this.seleccionesInscriptas) {
            if (seleccion.getPais().getNombre().equalsIgnoreCase(nombreBuscado)) {
                return seleccion;
            }
        }
        return null;
    }

    /**
     * Busca un jugador por su nombre entre todas las selecciones.
     *
     * @param nombre nombre
     * @return resultado de la operacion
     */
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

    /**
     * Registra un arbitro ya instanciado en el sistema.
     *
     * @param nuevoArbitro nuevoArbitro
     */
    public void registrarArbitro(Arbitro nuevoArbitro) {
        if (nuevoArbitro != null) {
            this.arbitros.add(nuevoArbitro);
        }
    }

}




