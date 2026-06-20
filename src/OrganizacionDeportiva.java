import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la organización deportiva: configuración de grupos y
 * fases, planificación de partidos y validación del equipo de arbitraje.
 */
public class OrganizacionDeportiva {
    private List<Partido> todosLosPartidos;
    private List<Grupo> gruposMundial;

    // Constructor


    public OrganizacionDeportiva(List<Partido> todosLosPartidos, List<Grupo> gruposMundial) {
        this.todosLosPartidos = todosLosPartidos;
        this.gruposMundial = gruposMundial;
    }

    // MÉTODOS CREADORES (El Main pasa los datos, la Gestora hace el "new")

    public Grupo configurarGrupo(String identificacion, String descripcion, Fase fase) {
        Grupo nuevoGrupo = new Grupo(identificacion, descripcion, fase);
        this.gruposMundial.add(nuevoGrupo);
        return nuevoGrupo;
    }

    public Partido planificarPartido(int fecha, int horario, int duracion, int tiempoAdicional,
                                     Estadio estadio, Fase fase,
                                     Participacion local, Participacion visitante) {
        Partido nuevoPartido = new Partido(fecha, horario, duracion, tiempoAdicional, estadio, fase, local, visitante);
        this.todosLosPartidos.add(nuevoPartido);
        return nuevoPartido;
    }
    /**
     * Busca un partido por su fecha.
     * @param fecha La fecha del partido a buscar (Ej. 20260615)
     * @return El objeto Partido si lo encuentra, o null si no existe.
     */
    public Partido buscarPartidoPorFecha(int fecha) {
        for (Partido partido : this.todosLosPartidos) {
            if (partido.getFecha() == fecha) {
                return partido;
            }
        }
        return null;
    }

    // Métodos para agregar objetos ya creados (Los mantenemos por las dudas)
    public void agregarPartido(Partido partido) {
        this.todosLosPartidos.add(partido);
    }

    public void agregarGrupo(Grupo grupo) {
        this.gruposMundial.add(grupo);
    }

    // GETTERS Y SETTERS
    public List<Partido> getTodosLosPartidos() { return this.todosLosPartidos; }
    public void setTodosLosPartidos(List<Partido> todosLosPartidos) { this.todosLosPartidos = todosLosPartidos; }

    public List<Grupo> getGruposMundial() { return this.gruposMundial; }
    public void setGruposMundial(List<Grupo> gruposMundial) { this.gruposMundial = gruposMundial; }

    // VALIDACIÓN
    public boolean tieneArbitroPrincipal(Partido partido) {
        if (partido == null) {
            return false;
        }
        if (partido.getArbitrajes() != null) {
            for (Arbitraje arbitraje : partido.getArbitrajes()) {
                if (arbitraje != null && arbitraje.getRol() == CategoriaArbitro.PRINCIPAL) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Valida que un partido tenga un equipo de arbitraje válido (con árbitro
     * principal). Lanza excepción si no lo cumple.
     */
    public void validarArbitroPrincipal(Partido partido) throws PartidoSinArbitroPrincipalException {
        if (!this.tieneArbitroPrincipal(partido)) {
            throw new PartidoSinArbitroPrincipalException("El partido no tiene asignado un árbitro con rol Principal.");
        }
    }



}