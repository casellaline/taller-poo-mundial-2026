import java.util.ArrayList;
import java.util.List;

public class GestionDelegaciones {
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;

    // 1. Constructor impecable (Las listas nacen vacías)
    public GestionDelegaciones() {
        this.paisesRegistrados = new ArrayList<>();
        this.seleccionesInscriptas = new ArrayList<>();
    }

    // 2. METODOS CREADORES (El Main pasa los Strings, la Gestora hace el "new")

    public Pais registrarPais(String nombre, String bandera) {
        Pais nuevoPais = new Pais(nombre, bandera);
        this.paisesRegistrados.add(nuevoPais);
        return nuevoPais;
    }

    public Seleccion registrarSeleccion(String federacion, String camPrin, String camSec, boolean cabeza, int ranking, Pais pais) {
        Seleccion nuevaSeleccion = new Seleccion(federacion, camPrin, camSec, cabeza, ranking);
        // Asociamos la selección al país acá mismo para matar dos pájaros de un tiro
        pais.asociarSeleccion(nuevaSeleccion);
        this.seleccionesInscriptas.add(nuevaSeleccion);
        return nuevaSeleccion;
    }

    // Inscribimos jugador creándolo directamente en la gestora
    public void inscribirJugador(String nombre, int anioNac, int dorsal, Posicion posicion, float peso, float altura, Seleccion seleccionDestino) throws JugadorYaInscriptoException {
        // Tu misma validación espectacular
        for (Seleccion seleccionActual : this.seleccionesInscriptas) {
            if (seleccionActual.getJugadores() != null) {
                for (Jugador jugadorRegistrado : seleccionActual.getJugadores()) {
                    if (jugadorRegistrado.getNombre().equalsIgnoreCase(nombre)) {
                        throw new JugadorYaInscriptoException("El jugador " + nombre + " ya está vinculado a " + seleccionActual.getNombreFederacion());
                    }
                }
            }
        }

        Jugador nuevoJugador = new Jugador(nombre, anioNac, dorsal, posicion, peso, altura, seleccionDestino);
        seleccionDestino.agregarJugador(nuevoJugador);
    }

    // Método para cumplir con el registro del cuerpo técnico exigido
    public void asignarCuerpoTecnico(String nombre, int anioNac, Rol rol, Seleccion seleccion) {
        CuerpoTecnico integrante = new CuerpoTecnico(nombre, anioNac, rol);
        seleccion.agregarCuerpoTecnico(integrante);
    }

    // 3. GETTERS Y SETTERS COMPLETO

    public List<Pais> getPaisesRegistrados() { return this.paisesRegistrados; }
    public void setPaisesRegistrados(List<Pais> paisesRegistrados) { this.paisesRegistrados = paisesRegistrados; }

    public List<Seleccion> getSeleccionesInscriptas() { return this.seleccionesInscriptas; }
    public void setSeleccionesInscriptas(List<Seleccion> seleccionesInscriptas) { this.seleccionesInscriptas = seleccionesInscriptas; }

    public Seleccion buscarSeleccionPorNombre(String nombreBuscado) {
        for (Seleccion seleccion : this.seleccionesInscriptas) {
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



