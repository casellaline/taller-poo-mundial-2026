import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la administracion de delegaciones: registro de
 * paises y selecciones, inscripcion de jugadores y cuerpo tecnico, y
 * busquedas de selecciones, jugadores y arbitros.
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
     * @param nombre nombre
     * @param bandera bandera
     * @return resultado de la operacion
     */
    public Pais registrarPais(String nombre, String bandera) {
        Pais nuevoPais = new Pais(nombre, bandera);
        this.paisesRegistrados.add(nuevoPais);
        return nuevoPais;
    }

    /**
     * Registra una nueva seleccion en el sistema.
     *
     * @param federacion federacion
     * @param camPrin camPrin
     * @param camSec camSec
     * @param cabeza cabeza
     * @param ranking ranking
     * @param pais pais
     * @return resultado de la operacion
     */
    public Seleccion registrarSeleccion(String federacion, String camPrin, String camSec,
                                        boolean cabeza, int ranking, Pais pais) {
        Seleccion nuevaSeleccion = new Seleccion(federacion, camPrin, camSec, cabeza, ranking);
        // Se asocia la selección al país
        pais.asociarSeleccion(nuevaSeleccion);
        this.seleccionesInscriptas.add(nuevaSeleccion);
        return nuevaSeleccion;
    }

    // Se inscribe un jugador creándolo directamente en la gestora
    /**
     * Inscribe un jugador en una seleccion, controlando que no este ya
     * vinculado a otra seleccion nacional.
     *
     * @param nombre nombre
     * @param anioNac anioNac
     * @param dorsal dorsal
     * @param posicion posicion
     * @param peso peso
     * @param altura altura
     * @param seleccionDestino seleccionDestino
     * @throws JugadorYaInscriptoException si la validacion correspondiente falla
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

    // Método para cumplir con el registro del cuerpo técnico
    /**
     * Crea y asigna un integrante del cuerpo tecnico a una seleccion.
     *
     * @param nombre nombre
     * @param anioNac anioNac
     * @param rol rol
     * @param seleccion seleccion
     */
    public void asignarCuerpoTecnico(String nombre, int anioNac, Rol rol, Seleccion seleccion) {
        CuerpoTecnico integrante = new CuerpoTecnico(nombre, anioNac, rol);
        seleccion.agregarCuerpoTecnico(integrante);
    }

    // GETTERS Y SETTERS

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
     * Busca una seleccion por el nombre de su federacion.
     *
     * @param nombreBuscado nombreBuscado
     * @return resultado de la operacion
     */
    public Seleccion buscarSeleccionPorNombre(String nombreBuscado) {
        for (Seleccion seleccion : this.seleccionesInscriptas) {
            if (seleccion.getNombreFederacion().equalsIgnoreCase(nombreBuscado)) {
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
                    return jugador; // Lo encontró y lo devuelve
                }
            }
        }
        return null; // Si revisó todas las selecciones y no estaba, devuelve nulo
    }

    /**
     * Busca un árbitro en el sistema por su nombre exacto.
     * @param nombre El nombre del árbitro a buscar.
     * @return El objeto Arbitro si lo encuentra, o null si no existe.
     */
    public Arbitro buscarArbitroPorNombre(String nombre) {
        // Recorremos la lista de árbitros
        for (Arbitro arbitro : this.arbitros) {
            // Comparamos ignorando mayúsculas y minúsculas para evitar errores de tipeo
            if (arbitro.getNombre().equalsIgnoreCase(nombre)) {
                return arbitro; // Retornamos la referencia en memoria del objeto
            }
        }
        return null; // Si termina el bucle y no lo encontró, retorna nulo
    }

    /**
     * Registra un nuevo arbitro en el sistema.
     *
     * @param nuevoArbitro nuevoArbitro
     */
    public void registrarArbitro(Arbitro nuevoArbitro) {
        if (nuevoArbitro != null) {
            this.arbitros.add(nuevoArbitro);
        }
    }

}




