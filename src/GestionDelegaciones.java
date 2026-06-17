import java.util.ArrayList;
import java.util.List;

public class GestionDelegaciones {
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;

    // Constructor
    public GestionDelegaciones() {
        this.paisesRegistrados = new ArrayList<>();
        this.seleccionesInscriptas = new ArrayList<>();
    }

    // METODOS CREADORES (El Main pasa los Strings, la Gestora hace el "new")

    public Pais registrarPais(String nombre, String bandera) {
        Pais nuevoPais = new Pais(nombre, bandera);
        this.paisesRegistrados.add(nuevoPais);
        return nuevoPais;
    }

    public Seleccion registrarSeleccion(String federacion, String camPrin, String camSec,
                                        boolean cabeza, int ranking, Pais pais) {
        Seleccion nuevaSeleccion = new Seleccion(federacion, camPrin, camSec, cabeza, ranking);
        // Asociamos la selección al país
        pais.asociarSeleccion(nuevaSeleccion);
        this.seleccionesInscriptas.add(nuevaSeleccion);
        return nuevaSeleccion;
    }

    // Inscribimos jugador creándolo directamente en la gestora
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




