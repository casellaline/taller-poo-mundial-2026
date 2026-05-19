import java.util.ArrayList;
import java.util.List;

public class Pais {
    private String nombre;
    private String bandera;
    private List<Sede> perteneceSede;
    private List<Arbitro> cuentaConArbitro;
    private List<Seleccion> representaSeleccion;
    //Constructor
    public Pais(){
        this.perteneceSede= new ArrayList<Sede>();
        this.cuentaConArbitro=new ArrayList<Arbitro>();
        this.representaSeleccion= new ArrayList<Seleccion>();

    }
    public Pais(String nombre, String bandera){
        this.nombre=nombre;
        this.bandera=bandera;
    }
    public Pais(String nombre, String bandera, List<Sede> perteneceSede, List<Arbitro> cuentaConArbitro, List<Seleccion> representaSeleccion){
        this.nombre=nombre;
        this.bandera=bandera;
        this.perteneceSede=perteneceSede;
        this.cuentaConArbitro=cuentaConArbitro;
        this.representaSeleccion=representaSeleccion;
    }
    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public String getBandera() {
        return bandera;
    }

    public void asociarSede(Sede sede) {
        this.perteneceSede.add(sede);
    }

    public void asociarArbitro(Arbitro arbitro) {
        this.cuentaConArbitro.add(arbitro);
    }

    public void asociarSeleccion(Seleccion seleccion) {
        this.representaSeleccion.add(seleccion);
    }
}
