//Configurar los Grupos y las Fases de eliminación, así como planificar los Partidos.
/**
 * Gestora encargada de la organización deportiva: configuración de grupos y
 * fases, planificación de partidos y validación del equipo de arbitraje.
 */
import java.util.ArrayList;
import java.util.List;
public class OrganizacionDeportiva {
    private List<Partido> todosLosPartidos;
    private List<Grupo> gruposMundial;

    //contructor
    public OrganizacionDeportiva(){
        this.todosLosPartidos=new ArrayList<Partido>();
        this.gruposMundial=new ArrayList<Grupo>();
    }
    public void agregarPartido(Partido partido){
        this.todosLosPartidos.add(partido);
    }
    public List<Partido> getTodosLosPartidos(){
        return this.todosLosPartidos;
    }
    public void agregarGrupo(Grupo grupo){
        this.gruposMundial.add(grupo);
    }
    public List<Grupo> getGruposMundial(){
        return this.gruposMundial;
    }

    //Verifica si un partido tiene asignado al menos un árbitro con rol
    public boolean tieneArbitroPrincipal(Partido partido){
        if (partido == null){
            return false;
        }
        for (Arbitraje arbitraje : partido.getArbitrajes()){
            if (arbitraje !=null && arbitraje.getRol()== CategoriaArbitro.PRINCIPAL){
                return true;
            }
        }
        return false;
    }

/**
 * Valida que un partido tenga un equipo de arbitraje válido (con árbitro
 * principal). Lanza excepción si no lo cumple.
 */
public void validarArbitroPrincipal (Partido partido) throws PartidoSinArbitroPrincipalException{
    if (!this.tieneArbitroPrincipal(partido)){
        throw new PartidoSinArbitroPrincipalException("El partido no tiene asignado un árbitro con rol Principal.");
    }
}



}
