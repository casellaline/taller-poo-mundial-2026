import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la administración de delegaciones: registro de países,
 * inscripción de selecciones y alta de jugadores con sus validaciones.
 */
public class GestionDelegaciones {
    // Declaración como interfaz List, tal como indicaron los profesores
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;

    // Constructores inicializando con la clase concreta ArrayList
    public GestionDelegaciones() {
        this.paisesRegistrados = new ArrayList<Pais>();
        this.seleccionesInscriptas = new ArrayList<Seleccion>();
    }

    // Métodos para la carga

    public void registrarPais(Pais pais) {
        this.paisesRegistrados.add(pais);
    }

    public void registrarNuevaSeleccion(Seleccion seleccion) {
        this.seleccionesInscriptas.add(seleccion);
    }

    public List<Pais> getPaisesRegistrados() {
        return this.paisesRegistrados;
    }

    public List<Seleccion> getSeleccionesInscriptas() {
        return this.seleccionesInscriptas;
    }

    /**
     * Inscribe un Jugador en una Selección.
     * Validando previamente que el jugador no esté vinculado a ninguna otra selección del sistema.
     */
    public void inscribirJugador(Seleccion seleccionDestino, Jugador nuevoJugador) throws JugadorYaInscriptoException {
        for (Seleccion seleccionActual : this.seleccionesInscriptas) {
            for (Jugador jugadorRegistrado : seleccionActual.getJugadores()) {
                if (jugadorRegistrado.getNombre().equalsIgnoreCase(nuevoJugador.getNombre())) {
                    throw new JugadorYaInscriptoException("Error de validación: El jugador " + nuevoJugador.getNombre() +
                            " ya se encuentra vinculado a la selección de " +
                            seleccionActual.getNombreFederacion());
                }
            }
        }
        seleccionDestino.agregarJugador(nuevoJugador);
    }

    // Método para buscar una selección por nombre
    public Seleccion buscarSeleccionPorNombre(String nombreBuscado) {
        // Bucle for-each limpio
        for (Seleccion seleccion : this.seleccionesInscriptas) {
            // Usamos ignoreCase para que sea más robusta la búsqueda
            if (seleccion.getNombreFederacion().equalsIgnoreCase(nombreBuscado)) {
                return seleccion;
            }
        }
        return null;
    }
}

/**
 *  public void cargarDatosHarcodeados() {
 *         Pais argentina = new Pais("Argentina", "bandera_arg.png");
 *         Pais brasil = new Pais("Brasil", "bandera_bra.png");
 *         this.registrarPais(argentina);
 *         this.registrarPais(brasil);
 *
 *         Seleccion selArg = new Seleccion("AFA", "Celeste", "Azul", true, 1);
 *         Seleccion selBra = new Seleccion("CBF", "Amarilla", "Azul", true, 2);
 *         argentina.asignarSeleccion(selArg);
 *         brasil.asignarSeleccion(selBra);
 *         this.registrarNuevaSeleccion(selArg);
 *         this.registrarNuevaSeleccion(selBra);
 *
 *         Jugador j1 = new Jugador("Messi", null, 10, Posicion.Delantero, 72.0, 1.70);
 *         Jugador j2 = new Jugador("Martinez", null, 23, Posicion.Arquero, 80.0, 1.95);
 *         Jugador j3 = new Jugador("Vinicius", null, 7, Posicion.Delantero, 73.0, 1.76);
 *         Jugador j4 = new Jugador("Alisson", null, 1, Posicion.Arquero, 91.0, 1.93);
 *         selArg.agregarJugador(j1);
 *         selArg.agregarJugador(j2);
 *         selBra.agregarJugador(j3);
 *         selBra.agregarJugador(j4);
 *
 *         Sede sede = new Sede("Buenos Aires", 25, "Templado", "GMT-3");
 *         Estadio estadio = new Estadio("Monumental", 84000);
 *         sede.agregarEstadio(estadio);
 *
 *         Partido partido = new Partido(null, null, 90, 5);
 *         Participacion partArg = new Participacion(true, partido, selArg);
 *         Participacion partBra = new Participacion(false, partido, selBra);
 *         partido.agregarParticipacion(partArg);
 *         partido.agregarParticipacion(partBra);
 *         estadio.agregarPartido(partido);
 *
 *         Evento gol1 = new Evento(TipoEvento.Gol, 23, j1);
 *         Evento amarilla1 = new Evento(TipoEvento.TarjetaAmarilla, 45, j3);
 *         partArg.agregarEvento(gol1);
 *         partBra.agregarEvento(amarilla1);
 *         partido.agregarEvento(gol1);
 *         partido.agregarEvento(amarilla1);
 *     }
 */



