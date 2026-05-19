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
    private List<Jugador> integraJugador;
    private List<DirectorTecnico> dirigeDT;
    private List<CuerpoTecnico> cuerpoTec;
    private List<Participacion> participaciones;

    //Constructores
    public Seleccion(){
        this.participaciones= new ArrayList<Participacion>();
        this.integraJugador=new ArrayList<Jugador>();
        this.dirigeDT=new ArrayList<DirectorTecnico>();
        this.cuerpoTec=new ArrayList<CuerpoTecnico>();
    }

    public Seleccion(String nombreFederacion, String camisetaPrincipal, String camisetaSecundaria, boolean cabezaGrupo, int rankingFIFA, Pais representaPais, Grupo seAgrupa, List<Jugador> integraJugador, List<DirectorTecnico> dirigeDT, List<CuerpoTecnico> cuerpoTec,List<Participacion>participaciones) {
        this.nombreFederacion = nombreFederacion;
        this.camisetaPrincipal = camisetaPrincipal;
        this.camisetaSecundaria = camisetaSecundaria;
        this.cabezaGrupo = cabezaGrupo;
        this.rankingFIFA = rankingFIFA;
        this.representaPais = representaPais;
        this.seAgrupa = seAgrupa;
        this.integraJugador = integraJugador;
        this.dirigeDT = dirigeDT;
        this.cuerpoTec = cuerpoTec;
        this.participaciones = participaciones;
    }
    //Getters y Setters

    public void agregarJugador(Jugador jugador) {
        this.integraJugador.add(jugador);
    }

    public void agregarDirectorTec(DirectorTecnico directorTec) {
        this.dirigeDT.add(directorTec);
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
