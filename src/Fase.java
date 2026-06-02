import java.util.ArrayList;
import java.util.List;

public class Fase {
    private NombreFase nombre;
    private List<Partido> partidos;
    private List<Grupo> grupos;
    //Constructores

    public Fase(){
        this.partidos= new ArrayList<Partido>();
        this.grupos= new ArrayList<Grupo>();
    }
    public Fase(NombreFase nombre) {
        this.nombre = nombre;
        this.partidos= new ArrayList<Partido>();
        this.grupos= new ArrayList<Grupo>();
    }
    //Getters y setters

    public NombreFase getNombre() {
        return nombre;
    }

    public void setNombre(NombreFase nombre) {
        this.nombre = nombre;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void asociarPartido(Partido partido){
        this.partidos.add(partido);
        partido.setCorrespondeFase(this);
    }

    public void asociarGrupo(Grupo grupo) {
        this.grupos.add(grupo);
        grupo.setIncluyeFase(this);
    }
}
