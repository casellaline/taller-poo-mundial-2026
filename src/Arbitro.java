import java.util.ArrayList;
import java.util.List;

public class Arbitro extends Persona{
    private int aniosExperiencia;
    private Pais cuentaConPais;
    private List<Arbitraje> arbitrajes;

    //Constructor
    public Arbitro(String nombre, int fecNacimiento, int aniosExperiencia, Pais cuentaConPais, List<Arbitraje> arbitrajes){
        super(nombre, fecNacimiento);
        this.aniosExperiencia=aniosExperiencia;
        this.cuentaConPais=cuentaConPais;
        this.arbitrajes=arbitrajes;
        this.arbitrajes = new ArrayList<>();//Se crea una lista vacia por la herencia
    }

    //Getters y Setters

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public Pais getCuentaConPais() {
        return cuentaConPais;
    }
    public void asignarPais(Pais pais){
        this.cuentaConPais=pais;
    }

    public void agregarArbitraje(Arbitraje arbi){
        this.arbitrajes.add(arbi);
    }

}
