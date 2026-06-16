import java.util.ArrayList;

public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    // Aplicamos ArrayList puro como vimos en las soluciones oficiales
    private ArrayList<Seleccion> selecciones;

    // Constructores
    public Grupo() {
        this.selecciones = new ArrayList<Seleccion>();
    }

    public Grupo(String identificacion, String descripcion, Fase incluyeFase) {
        // Asignación directa pura
        this.identificacion = identificacion;
        this.descripcion = descripcion;
        this.incluyeFase = incluyeFase;
        this.selecciones = new ArrayList<Seleccion>();
    }

    // Getters y Setters puros

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Fase getIncluyeFase() { return incluyeFase; }
    public void setIncluyeFase(Fase incluyeFase) { this.incluyeFase = incluyeFase; }

    public ArrayList<Seleccion> getSelecciones() { return selecciones; }
    public void setSelecciones(ArrayList<Seleccion> selecciones) { this.selecciones = selecciones; }

    public void asociarSeleccion(Seleccion seleccion) {
        this.selecciones.add(seleccion);
        seleccion.setGrupo(this);
    }

    // Métodos

    public int obtenerPuntos(Seleccion s) {
        int puntos = 0;

        // Confiamos en que la lista está inicializada, recorremos directo
        for (Participacion part : s.getParticipaciones()) {
            Partido partido = part.getPartido();

            // 1. Regla 0..1 de la cátedra: Validamos que haya partido y fase asignada
            // Comparamos los objetos Fase directamente
            if (partido != null && partido.getCorrespondeFase() != null &&
                    partido.getCorrespondeFase().equals(this.incluyeFase)) {

                Participacion rival = null;

                // 2. Identificamos al rival comparando referencias de memoria (==)
                if (partido.getEquipoLocal() == part) {
                    rival = partido.getEquipoVisitante();
                } else if (partido.getEquipoVisitante() == part) {
                    rival = partido.getEquipoLocal();
                }

                // 3. Regla 0..1: Validamos que el rival ya esté definido en el partido
                if (rival != null) {
                    int goles = part.cantidadGoles();
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