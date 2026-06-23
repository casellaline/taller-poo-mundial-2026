import java.util.ArrayList;

/**
 * Representa un grupo de la fase de grupos. Agrupa a las selecciones que
 * lo integran y calcula los puntos obtenidos segun los resultados de sus
 * partidos.
 */
public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    private ArrayList<Seleccion> selecciones;

    // Constructores

    /**
     * Crea una instancia vacia de {@code Grupo}.
     */
    public Grupo() {
        this.selecciones = new ArrayList<Seleccion>();
    }

    /**
     * Crea una instancia de {@code Grupo} con los datos indicados.
     *
     * @param identificacion identificacion
     * @param descripcion descripcion
     * @param incluyeFase incluyeFase
     */
    public Grupo(String identificacion, String descripcion, Fase incluyeFase) {
        this.identificacion = identificacion;
        this.descripcion = descripcion;
        this.incluyeFase = incluyeFase;
        this.selecciones = new ArrayList<Seleccion>();
    }

    // Getters y Setters puros

    /**
     * Devuelve identificacion.
     * @return identificacion
     */
    public String getIdentificacion() { return identificacion; }

    /**
     * Establece identificacion.
     *
     * @param identificacion identificacion
     */
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    /**
     * Devuelve descripcion.
     * @return descripcion
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Establece descripcion.
     *
     * @param descripcion descripcion
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Devuelve incluye fase.
     * @return incluye fase
     */
    public Fase getIncluyeFase() { return incluyeFase; }

    /**
     * Establece incluye fase.
     *
     * @param incluyeFase incluyeFase
     */
    public void setIncluyeFase(Fase incluyeFase) { this.incluyeFase = incluyeFase; }

    /**
     * Devuelve selecciones.
     * @return selecciones
     */
    public ArrayList<Seleccion> getSelecciones() { return selecciones; }

    /**
     * Establece selecciones.
     *
     * @param selecciones selecciones
     */
     void setSelecciones(ArrayList<Seleccion> selecciones) { this.selecciones = selecciones; }

    /**
     * Asocia una seleccion al grupo y establece la relacion inversa.
     *
     * @param seleccion seleccion
     */
    public void asociarSeleccion(Seleccion seleccion) {
        this.selecciones.add(seleccion);
        seleccion.setGrupo(this);
    }

    // Métodos

    /**
     * Calcula los puntos obtenidos por una seleccion en este grupo
     * (3 por victoria, 1 por empate, 0 por derrota).
     *
     * @param s s
     * @return resultado de la operacion
     */
    public int obtenerPuntos(Seleccion s) {
        int puntos = 0;

        for (Participacion parti : s.getParticipaciones()) {
            Partido partido = parti.getPartido();

            if (partido != null && partido.getCorrespondeFase() != null &&
                    partido.getCorrespondeFase().equals(this.incluyeFase)) {

                Participacion rival = null;

                if (partido.getEquipoLocal().equals(parti)) {
                    rival = partido.getEquipoVisitante();
                } else if (partido.getEquipoVisitante() == parti) {
                    rival = partido.getEquipoLocal();
                }

                if (rival != null) {
                    int goles = parti.cantidadGoles();
                    int golesRival = rival.cantidadGoles();

                    if (goles > golesRival) {
                        puntos += 3; // Victoria
                    } else if (goles == golesRival) {
                        puntos += 1; // Empate
                    }
                }
            }
        }
        return puntos;
    }
}