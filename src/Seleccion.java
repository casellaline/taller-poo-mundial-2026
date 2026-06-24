import java.util.ArrayList;
import java.util.List;

/**
 * Representa a una seleccion nacional inscripta en el Mundial. Agrupa a
 * sus jugadores, directores tecnicos, cuerpo tecnico y participaciones en
 * los distintos partidos.
 */
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

    /**
     * Crea una instancia vacia de {@code Seleccion}.
     */
    public Seleccion() {
        this.jugadores = new ArrayList<Jugador>();
        this.directoresTecnicos = new ArrayList<DirectorTecnico>();
        this.cuerpoTec = new ArrayList<CuerpoTecnico>();
        this.participaciones = new ArrayList<Participacion>();
    }


    public Seleccion(String nombreFederacion, String camisetaPrincipal, String camisetaSecundaria,
                     boolean cabezaGrupo, int rankingFIFA) {
        this.nombreFederacion = nombreFederacion;
        this.camisetaPrincipal = camisetaPrincipal;
        this.camisetaSecundaria = camisetaSecundaria;
        this.cabezaGrupo = cabezaGrupo;
        this.rankingFIFA = rankingFIFA;

        this.jugadores = new ArrayList<Jugador>();
        this.directoresTecnicos = new ArrayList<DirectorTecnico>();
        this.cuerpoTec = new ArrayList<CuerpoTecnico>();
        this.participaciones = new ArrayList<Participacion>();
    }

    // Getters y Setters

    /**
     * Devuelve nombre federacion.
     * @return nombre federacion
     */
    public String getNombreFederacion() { return nombreFederacion; }
    /**
     * Establece nombre federacion.
     *
     * @param nombreFederacion nombreFederacion
     */
     void setNombreFederacion(String nombreFederacion) { this.nombreFederacion = nombreFederacion; }

    /**
     * Devuelve camiseta principal.
     * @return camiseta principal
     */
    public String getCamisetaPrincipal() { return camisetaPrincipal; }

    /**
     * Establece camiseta principal.
     *
     * @param camisetaPrincipal camisetaPrincipal
     */
    public void setCamisetaPrincipal(String camisetaPrincipal) { this.camisetaPrincipal = camisetaPrincipal; }

    /**
     * Devuelve camiseta secundaria.
     * @return camiseta secundaria
     */
    public String getCamisetaSecundaria() { return camisetaSecundaria; }

    /**
     * Establece camiseta secundaria.
     *
     * @param camisetaSecundaria camisetaSecundaria
     */
    public void setCamisetaSecundaria(String camisetaSecundaria) { this.camisetaSecundaria = camisetaSecundaria; }

    /**
     * Devuelve cabeza grupo.
     * @return cabeza grupo
     */
    public boolean isCabezaGrupo() { return cabezaGrupo; }
    /**
     * Establece cabeza grupo.
     *
     * @param cabezaGrupo cabezaGrupo
     */
    public void setCabezaGrupo(boolean cabezaGrupo) { this.cabezaGrupo = cabezaGrupo; }

    /**
     * Devuelve ranking fifa.
     * @return ranking fifa
     */
    public int getRankingFIFA() { return rankingFIFA; }

    /**
     * Establece ranking fifa.
     *
     * @param rankingFIFA rankingFIFA
     */
    public void setRankingFIFA(int rankingFIFA) { this.rankingFIFA = rankingFIFA; }

    /**
     * Devuelve pais.
     * @return pais
     */
    public Pais getPais() { return pais; }
    /**
     * Establece pais.
     *
     * @param pais pais
     */
    public void setPais(Pais pais) { this.pais = pais; }

    /**
     * Devuelve grupo.
     * @return grupo
     */
    public Grupo getGrupo() { return grupo; }

    /**
     * Establece grupo.
     *
     * @param grupo grupo
     */
    public void setGrupo(Grupo grupo) { this.grupo = grupo; }

    /**
     * Devuelve jugadores.
     * @return jugadores
     */
    public List<Jugador> getJugadores() { return jugadores; }
    /**
     * Establece jugadores.
     *
     * @param jugadores jugadores
     */
    public void setJugadores(List<Jugador> jugadores) { this.jugadores = jugadores; }

    /**
     * Devuelve directores tecnicos.
     * @return directores tecnicos
     */
    public List<DirectorTecnico> getDirectoresTecnicos() { return directoresTecnicos; }
    /**
     * Establece directores tecnicos.
     *
     * @param directoresTecnicos directoresTecnicos
     */
    public void setDirectoresTecnicos(List<DirectorTecnico> directoresTecnicos) { this.directoresTecnicos = directoresTecnicos; }

    /**
     * Devuelve cuerpo tec.
     * @return cuerpo tec
     */
    public List<CuerpoTecnico> getCuerpoTec() { return cuerpoTec; }
    /**
     * Establece cuerpo tec.
     *
     * @param cuerpoTec cuerpoTec
     */
    public void setCuerpoTec(List<CuerpoTecnico> cuerpoTec) { this.cuerpoTec = cuerpoTec; }

    /**
     * Devuelve participaciones.
     * @return participaciones
     */
    public List<Participacion> getParticipaciones() { return participaciones; }

    /**
     * Establece participaciones.
     *
     * @param participaciones participaciones
     */
    public void setParticipaciones(List<Participacion> participaciones) { this.participaciones = participaciones; }

    // Métodos de Asociación Bidireccional

    /**
     * Agrega un jugador a la seleccion y establece la relacion.
     *
     * @param jugador jugador
     */
    public void agregarJugador(Jugador jugador) {
        this.jugadores.add(jugador);
        jugador.setSeleccion(this);
    }

    /**
     * Agrega un director tecnico a la seleccion y establece la relacion.
     *
     * @param DT DT
     */
    public void agregarDirectorTecnico(DirectorTecnico DT) {
        this.directoresTecnicos.add(DT);
        DT.setSeleccion(this); // Avisamos al DT de qué selección es
    }

    /**
     * Agrega un integrante del cuerpo tecnico y establece la relacion.
     *
     * @param CT CT
     */
    public void agregarCuerpoTecnico(CuerpoTecnico CT) {
        this.cuerpoTec.add(CT);
        CT.setSeleccion(this); // Avisamos al Cuerpo Técnico
    }
    /**
     * Agrega una participacion a la seleccion y establece la relacion.
     *
     * @param participacion participacion
     */
    public void agregarParticipacion(Participacion participacion) {
        this.participaciones.add(participacion);
        participacion.asociarSeleccion(this);
    }
}