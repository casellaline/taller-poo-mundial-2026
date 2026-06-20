import java.util.ArrayList;
import java.util.List;

public class RepoMundial {
    private List<Pais> paises;
    private List<Seleccion> selecciones;
    private List<Sede> sedes;
    private List<Partido> partidos;
    private List<Grupo> gruposMundial;
    private List<Arbitro> arbitros;

    public RepoMundial() {
        this.paises = new ArrayList<>();
        this.selecciones = new ArrayList<>();
        this.sedes = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.gruposMundial = new ArrayList<>();
        this.arbitros= new ArrayList<>();
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public List<Grupo> getGruposMundial() {
        return gruposMundial;
    }

    public List<Arbitro> getArbitros(){
        return arbitros;
    }
}
