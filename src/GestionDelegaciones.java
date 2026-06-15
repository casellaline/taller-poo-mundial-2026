//Gestión de Infraestructura: Registrar Sedes y Estadios con su respectiva capacidad.
//
// Gestionar los Países participantes, sus Selecciones, cuerpos técnicos y la lista de Jugadores.

import java.util.ArrayList;
import java.util.List;

public class GestionDelegaciones {
    private List<Pais> paisesRegistrados;
    private List<Seleccion> seleccionesInscriptas;

    //Constructores
    public GestionDelegaciones(){
        this.paisesRegistrados= new ArrayList<Pais>();
        this.seleccionesInscriptas= new ArrayList<Seleccion>();
    }

    //metodos para la carga

    public void registrarPais (Pais pais){
        this.paisesRegistrados.add(pais);
    }
    public void registrarNuevaSeleccion(Seleccion seleccion){
        this.seleccionesInscriptas.add(seleccion);
    }

    /**
     * Intenta inscribir un Jugador en una Selección.
     * Retorna true si tuvo éxito, o false si el jugador ya pertenece a otra.
     */

    public void inscribirJugador(Seleccion seleccionDestino, Jugador nuevoJugador) throws JugadorYaInscriptoException {
        for (Seleccion seleccionActual : seleccionesInscriptas) {
            for (Jugador jugadorRegistrado : seleccionActual.getJugadores()) {
                if (jugadorRegistrado == nuevoJugador) {
                    throw new JugadorYaInscriptoException("Error de validación: El jugador " + nuevoJugador.getNombre() +
                            " ya se encuentra vinculado a la selección de " +
                            seleccionActual.getNombreFederacion());
                }
            }
        }
        seleccionDestino.agregarJugador(nuevoJugador);
    }

    //Metodo para buscar una selección por nombre

    public Seleccion buscarSeleccionPorNombre(String nombreBuscado){
        for (int i=0; i<seleccionesInscriptas.size();i++){
            Seleccion seleccion= seleccionesInscriptas.get(i);
            if (seleccion.getNombreFederacion().equals(nombreBuscado)){
                return seleccion;
            }
        }
        return null;
    }
}
