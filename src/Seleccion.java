import java.util.ArrayList;
import java.util.List;

public class Seleccion {
    private String nombreFederacion;
    private String camisetaPrincipal;
    private String camisetaSecundaria;
    private boolean cabezaGrupo;
    private int rankingFIFA;

    private Pais pais;
    private Grupo grupo;

    private List<Jugador> jugadores;
    private List<DirectorTecnico> directoresTecnicos;
    private List<CuerpoTecnico> cuerpoTec;
    private List<Participacion> participaciones;

    // Constructores

    public Seleccion() {
        this.jugadores = new ArrayList<Jugador>();
        this.directoresTecnicos = new ArrayList<DirectorTecnico>();
        this.cuerpoTec = new ArrayList<CuerpoTecnico>();
        this.participaciones = new ArrayList<Participacion>();
    }

    // Constructor limpio: solo datos primitivos y Strings
    public Seleccion(String nombreFederacion, String camisetaPrincipal, String camisetaSecundaria,
                     boolean cabezaGrupo, int rankingFIFA) {
        this.nombreFederacion = nombreFederacion;
        this.camisetaPrincipal = camisetaPrincipal;
        this.camisetaSecundaria = camisetaSecundaria;
        this.cabezaGrupo = cabezaGrupo;
        this.rankingFIFA = rankingFIFA;

        // Inicialización obligatoria de colecciones
        this.jugadores = new ArrayList<Jugador>();
        this.directoresTecnicos = new ArrayList<DirectorTecnico>();
        this.cuerpoTec = new ArrayList<CuerpoTecnico>();
        this.participaciones = new ArrayList<Participacion>();
    }

    // Getters y Setters

    public String getNombreFederacion() { return nombreFederacion; }
    public void setNombreFederacion(String nombreFederacion) { this.nombreFederacion = nombreFederacion; }

    public String getCamisetaPrincipal() { return camisetaPrincipal; }
    public void setCamisetaPrincipal(String camisetaPrincipal) { this.camisetaPrincipal = camisetaPrincipal; }

    public String getCamisetaSecundaria() { return camisetaSecundaria; }
    public void setCamisetaSecundaria(String camisetaSecundaria) { this.camisetaSecundaria = camisetaSecundaria; }

    public boolean isCabezaGrupo() { return cabezaGrupo; }
    public void setCabezaGrupo(boolean cabezaGrupo) { this.cabezaGrupo = cabezaGrupo; }

    public int getRankingFIFA() { return rankingFIFA; }
    public void setRankingFIFA(int rankingFIFA) { this.rankingFIFA = rankingFIFA; }

    public Pais getPais() { return pais; }
    public void setPais(Pais pais) { this.pais = pais; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    public List<Jugador> getJugadores() { return jugadores; }
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }

    public List<DirectorTecnico> getDirectoresTecnicos() { return directoresTecnicos; }
    public void setDirectoresTecnicos(List<DirectorTecnico> directoresTecnicos) { this.directoresTecnicos = directoresTecnicos; }

    public List<CuerpoTecnico> getCuerpoTec() { return cuerpoTec; }
    public void setCuerpoTec(List<CuerpoTecnico> cuerpoTec) { this.cuerpoTec = cuerpoTec; }

    public List<Participacion> getParticipaciones() { return participaciones; }
    public void setParticipaciones(List<Participacion> participaciones) { this.participaciones = participaciones; }

    // Métodos de Asociación Bidireccional

    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
        jugador.setSeleccion(this);
    }

    public void agregarDirectorTecnico(DirectorTecnico DT) {
        this.directoresTecnicos.add(DT);
        DT.setSeleccion(this); // Avisamos al DT de qué selección es
    }

    public void agregarCuerpoTecnico(CuerpoTecnico CT) {
        this.cuerpoTec.add(CT);
        CT.setSeleccion(this); // Avisamos al Cuerpo Técnico
    }

    public void agregarParticipacion(Participacion participacion) {
        this.participaciones.add(participacion);
        participacion.asociarSeleccion(this);
    }
}