//A

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private String nombre;
    private String bandera;
    private List<Sede> sedes;
    private List<Arbitro> arbitros;
    private List<Seleccion> selecciones;
    //Constructor
    public Pais(){
        this.sedes= new ArrayList<Sede>();
        this.arbitros=new ArrayList<Arbitro>();
        this.selecciones= new ArrayList<Seleccion>();

    }
    public Pais(String nombre, String bandera){
        this.nombre=nombre;
        this.bandera=bandera;
        this.sedes= new ArrayList<Sede>();
        this.arbitros=new ArrayList<Arbitro>();
        this.selecciones= new ArrayList<Seleccion>();
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    public void asociarSede(Sede sede){
        this.sedes.add(sede);
        sede.setPertenecePais(this);
    }

    public List<Arbitro> getArbitros() {
        return arbitros;
    }

    public void setArbitros(List<Arbitro> arbitros) {
        this.arbitros = arbitros;
    }

    public void agregarArbitros(Arbitro arbitro){
        this.arbitros.add(arbitro);
        arbitro.setCuentaConPais(this);
    }

    public List<Seleccion> getSeleccion() {
        return selecciones;
    }

    public void setSeleccion(List<Seleccion> seleccion) {
        this.selecciones = seleccion;
    }
    public void asociarSeleccion(Seleccion seleccion){
        this.selecciones.add(seleccion);
        seleccion.setPais(this);
    }
}
