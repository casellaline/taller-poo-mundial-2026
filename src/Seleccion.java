import java.util.ArrayList;
import java.util.List;

public class Seleccion {
    private String nombreFederacion;
    private String camisetaPrincipal;
    private String camisetaSecundaria;
    private boolean cabezaGrupo;
    private int rankingFIFA;
    private Pais representaPais;
    private Grupo seAgrupa;
    private List<Jugador> Jugadores;
    private List<DirectorTecnico> directoresTecnicos;
    private List<CuerpoTecnico> cuerpoTec;
    private List<Participacion> participaciones;

    //Constructores
    public Seleccion(){
        this.participaciones= new ArrayList<Participacion>();
        this.Jugadores=new ArrayList<Jugador>();
        this.directoresTecnicos=new ArrayList<DirectorTecnico>();
        this.cuerpoTec=new ArrayList<CuerpoTecnico>();
    }

    public Seleccion(String nombreFederacion, String camisetaPrincipal, String camisetaSecundaria, boolean cabezaGrupo, int rankingFIFA, Pais representaPais, Grupo seAgrupa) {
        this.nombreFederacion = nombreFederacion;
        this.camisetaPrincipal = camisetaPrincipal;
        this.camisetaSecundaria = camisetaSecundaria;
        this.cabezaGrupo = cabezaGrupo;
        this.rankingFIFA = rankingFIFA;
        this.representaPais = representaPais;
        this.seAgrupa = seAgrupa;
        this.Jugadores = Jugadores;
        this.directoresTecnicos = directoresTecnicos;
        this.cuerpoTec = cuerpoTec;
        this.participaciones = participaciones;
    }
    //Getters y Setters

    public void agregarJugador(Jugador jugador) {
        this.Jugadores.add(jugador);
    }

    public void agregarDirectorTec(DirectorTecnico directorTec) {
        this.directoresTecnicos.add(directorTec);
    }

    public void agregarCuerpoTec(CuerpoTecnico cuerpoTec) {
        this.cuerpoTec.add(cuerpoTec);
    }

    public String getNombreFederacion() {
        return nombreFederacion;
    }

    public String getCamisetaPrincipal() {
        return camisetaPrincipal;
    }

    public String getCamisetaSecundaria() {
        return camisetaSecundaria;
    }

    public boolean isCabezaGrupo() {
        return cabezaGrupo;
    }

    public int getRankingFIFA() {
        return rankingFIFA;
    }

    public Pais getRepresentaPais() {
        return representaPais;
    }

    public Grupo getSeAgrupa() {
        return seAgrupa;
    }

    public void agregarParticipacion(Participacion participacion){
        this.participaciones.add(participacion);
    }
}
