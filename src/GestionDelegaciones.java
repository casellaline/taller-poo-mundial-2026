//Gestión de Infraestructura: Registrar Sedes y Estadios con su respectiva capacidad.
//
// Gestionar los Países participantes, sus Selecciones, cuerpos técnicos y la lista de Jugadores.

import java.util.ArrayList;
import java.util.List;

public class GestionDelegaciones {
    private List<Pais> paises;
    private List<Sede> sedes;
    private List<Seleccion> selecciones;

    //Constructores
    public GestionDelegaciones(){
        this.paises= new ArrayList<Pais>();
        this.sedes= new ArrayList<Sede>();
        this.selecciones= new ArrayList<Seleccion>();
        inicializarDatosBase();
    }

    //metodo para la carga inicial

    private void inicializarDatosBase(){
        Pais argentina= new Pais("Argentina","Celeste y Blanco");
        this.paises.add(argentina);

        Sede sedeBsAs = new Sede("Buenos Aires", 25.0f,"Templado","GMT-3",argentina );
        this.sedes.add(sedeBsAs);

        Estadio monumental = new Estadio("Monumental",80000,sedeBsAs);
        sedeBsAs.agregarEstadio(monumental);

    }
    //metodos para la carga
    public Sede registrarNuevaSede(){}

    public void registrarNuevoEstadio(Sede sedeDestino){}

    public void registrarNuevaSeleccion(Pais pais){}

    public void agregarJugadorASeleccion(Seleccion seleccion){}

    public void agregarCuerpoTecnico(Seleccion seleccion){}
}
