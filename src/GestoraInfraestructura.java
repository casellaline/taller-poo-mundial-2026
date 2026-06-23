import java.util.ArrayList;
import java.util.List;

/**
 * Gestora encargada de la infraestructura del torneo: configuracion del
 * Mundial y registro de sedes y estadios con su capacidad.
 */
public class GestoraInfraestructura {
    private Mundial mundial;
    private List<Sede> sedes;

    // Constructor

    /**
     * Crea una instancia de {@code GestoraInfraestructura} con los datos indicados.
     *
     * @param sedes sedes
     */
    public GestoraInfraestructura(List<Sede> sedes) {
        this.sedes = sedes;
    }

    // Método para instanciar el Mundial
    /**
     * Configura los datos generales del Mundial (anio, mascota y fechas).
     *
     * @param anio anio
     * @param mascota mascota
     * @param fechaDesde fechaDesde
     * @param fechaHasta fechaHasta
     */
    public void configurarMundial(int anio, String mascota, int fechaDesde, int fechaHasta) {
        this.mundial = new Mundial(anio, mascota, fechaDesde, fechaHasta);
    }

    // Método para crear una Sede, guardarla en la lista de la gestora y asociarla al mundial
    /**
     * Registra una nueva sede (ciudad) del torneo.
     *
     * @param ciudad ciudad
     * @param alturaNivelMar alturaNivelMar
     * @param clima clima
     * @param zonaHoraria zonaHoraria
     * @return resultado de la operacion
     */
    public Sede registrarSede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria) {
        Sede nuevaSede = new Sede(ciudad, alturaNivelMar, clima, zonaHoraria);
        this.sedes.add(nuevaSede);

        if (this.mundial != null) {
            this.mundial.asociarSede(nuevaSede);
        }
        return nuevaSede;
    }

    // Método para crear el estadio y agregarlo adentro de la sede
    /**
     * Registra un nuevo estadio asociado a una sede.
     *
     * @param nombre nombre
     * @param capacidad capacidad
     * @param sede sede
     * @return resultado de la operacion
     */
    public Estadio registrarEstadio(String nombre, int capacidad, Sede sede) {
        Estadio nuevoEstadio = new Estadio(nombre, capacidad, sede);
        sede.agregarEstadio(nuevoEstadio);
        return nuevoEstadio;
    }

    // Getters
    /**
     * Devuelve mundial.
     * @return mundial
     */
    public Mundial getMundial() {
        return mundial;
    }

    /**
     * Devuelve sedes.
     * @return sedes
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * Establece sedes.
     *
     * @param sedes sedes
     */
    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }
}