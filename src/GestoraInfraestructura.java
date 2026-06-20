import java.util.ArrayList;
import java.util.List;

public class GestoraInfraestructura {
    private Mundial mundial;
    private List<Sede> sedes;

    // Constructor

    public GestoraInfraestructura(List<Sede> sedes) {
        this.sedes = sedes;
    }

    // Método para instanciar el Mundial
    public void configurarMundial(int anio, String mascota, int fechaDesde, int fechaHasta) {
        this.mundial = new Mundial(anio, mascota, fechaDesde, fechaHasta);
    }

    // Método para crear una Sede, guardarla en la lista de la gestora y asociarla al mundial
    public Sede registrarSede(String ciudad, float alturaNivelMar, String clima, String zonaHoraria) {
        Sede nuevaSede = new Sede(ciudad, alturaNivelMar, clima, zonaHoraria);
        this.sedes.add(nuevaSede);

        if (this.mundial != null) {
            this.mundial.asociarSede(nuevaSede);
        }
        return nuevaSede;
    }

    // Método para crear el estadio y agregarlo adentro de la sede
    public Estadio registrarEstadio(String nombre, int capacidad, Sede sede) {
        Estadio nuevoEstadio = new Estadio(nombre, capacidad, sede);
        sede.agregarEstadio(nuevoEstadio);
        return nuevoEstadio;
    }

    // Getters
    public Mundial getMundial() {
        return mundial;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }
}