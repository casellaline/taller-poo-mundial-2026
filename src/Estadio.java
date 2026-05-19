import java.util.ArrayList;
import java.util.List;

public class Estadio {
    private String nombre;
    private int capacidad;
    private Sede tieneSede;
    private List<Partido> seDesarrollaPartido;
    //Constructores
    public Estadio(){
        this.seDesarrollaPartido= new ArrayList<Partido>();
    }
    public Estadio(String nombre, int capacidad, Sede tieneSede, List<Partido> seDesarrollaPartido){
        this.nombre=nombre;
        this.capacidad=capacidad;
        this.tieneSede=tieneSede;
        this.seDesarrollaPartido=seDesarrollaPartido;
    }
    //Getters
    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public Sede getTieneSede() {
        return tieneSede;
    }

    public void agregarPartido(Partido partido) {
        this.seDesarrollaPartido.add(partido);
    }
}

