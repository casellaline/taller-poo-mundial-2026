import java.util.ArrayList;
import java.util.List;

public class Arbitro extends Persona{
    private int aniosExperiencia;
    private Pais cuentaConPais;
    private List<Arbitraje> arbitrajes;

    //Constructores
    public Arbitro(){
        this.arbitrajes= new ArrayList<Arbitraje>();
    }

    public Arbitro(String nombre, int fecNacimiento, int aniosExperiencia,
                   Pais cuentaConPais){
        super(nombre, fecNacimiento);
        this.aniosExperiencia=aniosExperiencia;
        this.cuentaConPais=cuentaConPais;
        this.arbitrajes = new ArrayList<>();//Se crea una lista vacia por la herencia
    }

    //Getters y Setters


    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Pais getCuentaConPais() {
        return cuentaConPais;
    }

    public void setCuentaConPais(Pais cuentaConPais) {
        this.cuentaConPais = cuentaConPais;
    }

    public List<Arbitraje> getArbitrajes() {
        return arbitrajes;
    }

    public void setArbitrajes(List<Arbitraje> arbitrajes) {
        this.arbitrajes = arbitrajes;
    }

    public void agregarArbitraje(Arbitraje arbitraje){
        this.arbitrajes.add(arbitraje);
        arbitraje.setArbitro(this);
    }
}
