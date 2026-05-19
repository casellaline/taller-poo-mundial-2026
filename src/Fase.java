import java.util.ArrayList;
import java.util.List;

public class Fase {
    private NombreFase nombre;
    private List<Partido> correspondePartido;
    private List<Grupo> incluyeGrupo;
    //Constructores
    public Fase(){
        this.correspondePartido= new ArrayList<Partido>();
        this.incluyeGrupo= new ArrayList<Grupo>();
    }
    public Fase(NombreFase nombre, List<Partido> correspondePartido) {
        this.nombre = nombre;
        this.correspondePartido = correspondePartido;
    }
    //Getters y setters

    public NombreFase getNombre() {
        return nombre;
    }

    public void asociarGrupo(Grupo grupo) {
        this.incluyeGrupo.add(grupo);
    }
}
