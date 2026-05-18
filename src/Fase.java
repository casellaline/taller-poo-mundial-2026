public class Fase {
    private NombreFase nombre;
    private Partido correspondePartido;
    private Grupo incluyeGrupo;
    //Constructores

    public Fase(NombreFase nombre, Partido correspondePartido) {
        this.nombre = nombre;
        this.correspondePartido = correspondePartido;
        this.incluyeGrupo=null;
    }
    //Getters y setters

    public NombreFase getNombre() {
        return nombre;
    }

    public Partido getCorrespondePartido() {
        return correspondePartido;
    }

    public Grupo getIncluyeGrupo() {
        return incluyeGrupo;
    }

    public void asociarGrupo(Grupo grupo) {
        this.incluyeGrupo = grupo;
    }
}
