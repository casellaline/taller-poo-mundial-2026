public class Seleccion {
    private String nombreFederacion;
    private String camisetaPrincipal;
    private String camisetaSecundaria;
    private boolean cabezaGrupo;
    private int rankingFIFA;
    private Pais representaPais;
    private Grupo seAgrupa;
    private Jugador integraJugador;
    private DirectorTecnico dirigeDT;
    private CuerpoTecnico cuerpoTec;
    private Participacion participacion;

    public Seleccion(String nombreFederacion, String camisetaPrincipal, String camisetaSecundaria, boolean cabezaGrupo, int rankingFIFA, Pais representaPais, Grupo seAgrupa, Jugador integraJugador, DirectorTecnico dirigeDT, CuerpoTecnico cuerpoTec) {
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
        this.participacion = null;
    }
    //Getters y Setters

    public void agregarJugador(Jugador jugador) {
        this.integraJugador = jugador;
    }

    public void agregarDirectorTec(DirectorTecnico directorTec) {
        this.dirigeDT = directorTec;
    }

    public void agregarCuerpoTec(CuerpoTecnico cuerpoTec) {
        this.cuerpoTec = cuerpoTec;
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

    public Jugador getIntegraJugador() {
        return integraJugador;
    }

    public DirectorTecnico getDirigeDT() {
        return dirigeDT;
    }

    public CuerpoTecnico getCuerpoTec() {
        return cuerpoTec;
    }

    public Participacion getParticipacion() {
        return participacion;
    }
}
