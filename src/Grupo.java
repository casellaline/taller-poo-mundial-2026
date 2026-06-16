import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private String identificacion;
    private String descripcion;
    private Fase incluyeFase;
    private List<Seleccion> selecciones;
    //Constructor

    public Grupo(){
        this.selecciones=new ArrayList<Seleccion>();
    }

    public Grupo(String identificacion, String descripcion, Fase incluyeFase) {
        this.identificacion = identificacion;
        this.descripcion = descripcion;
        this.incluyeFase = incluyeFase;
        this.selecciones = new ArrayList<Seleccion>();
    }
    //Getter

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Fase getIncluyeFase() {
        return incluyeFase;
    }

    public void setIncluyeFase(Fase incluyeFase) {
        this.incluyeFase = incluyeFase;
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    public void setSelecciones(List<Seleccion> selecciones) {
        this.selecciones = selecciones;
    }

    public void asociarSeleccion(Seleccion seleccion){
        this.selecciones.add(seleccion);
        seleccion.setGrupo(this);
    }
    //Metodos

    public int obtenerPuntos (Seleccion s){
        int puntos =0;

        if (s == null || s.getParticipaciones()==null){
            return 0;
        }
        for(Participacion part : s.getParticipaciones()){
            if (part != null && part.getPartido() != null){
                Partido partido = part.getPartido();
                Fase faseDelPartido = partido.getCorrespondeFase();

                //Verificamos que el partido sea específicamente de la fase de grupos
                if (faseDelPartido != null && faseDelPartido.getNombre()== NombreFase.GRUPOS){
                    Participacion rival = null;

                    // 4. Identificamos al rival de forma segura usando .equals()
                    if (partido.getEquipoLocal() !=null && !partido.getEquipoLocal().equals(part)){
                        rival = partido.getEquipoLocal();
                    } else if (partido.getEquipoVisitante() !=null && !partido.getEquipoVisitante().equals(part)) {
                       rival=partido.getEquipoVisitante();
                    }

                    // 5. Comparamos los goles y sumamos si encontramos al rival
                    if (rival != null){
                        int goles= part.cantidadGoles();
                        int golesRival =rival.cantidadGoles();

                        if(goles > golesRival){
                            puntos +=3; //Victoria
                        } else if (goles == golesRival) {
                            puntos +=1; // Empate
                        }
                    }
                }
            }
        }
        return puntos;
    }

    /**
    public int obtenerPuntos(Seleccion s) {
        int puntos = 0;
        // Recorremos las participaciones de la selección directamente
        for (Participacion part : s.getParticipaciones()) {
            Partido p = part.getPartido();
            if (p == null) continue;

            if (p.getCorrespondeFase() != null && p.getCorrespondeFase().equals(this.incluyeFase)) {

                int golesPropios = part.cantidadGoles();

                // Identificamos quién es el rival en este partido para comparar goles
                Participacion rival = (p.getEquipoLocal() == part) ? p.getEquipoVisitante() : p.getEquipoLocal();
                int golesRivales = rival.cantidadGoles();

                if (golesPropios > golesRivales) {
                    puntos += 3; // Victoria
                } else if (golesPropios == golesRivales) {
                    puntos += 1; // Empate
                }
            }
        }

        return puntos;
    }
     */
}
