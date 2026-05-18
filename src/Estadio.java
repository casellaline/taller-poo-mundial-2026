public class Estadio {
    private String nombre;
    private int capacidad;
    private Sede tieneSede;
    private Partido seDesarrollaPartido;
    //Constructores
    public Estadio(String nombre, int capacidad, Sede tieneSede, Partido seDesarrollaPartido){
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

    public Partido getSeDesarrollaPartido() {
        return seDesarrollaPartido;
    }
}

