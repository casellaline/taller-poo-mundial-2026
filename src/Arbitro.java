public class Arbitro extends Persona{
    private int aniosExperiencia;
    private Pais cuentaConPais;
    private Arbitraje arbitraje;
    //Constructor
    public Arbitro(String nombre, int fecNacimiento, int aniosExperiencia){
        super(nombre, fecNacimiento);
        this.aniosExperiencia=aniosExperiencia;
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

    public Arbitraje getArbitraje() {
        return arbitraje;
    }

    public void asignarArbitraje(Arbitraje arbitraje){
        this.arbitraje=arbitraje;
    }

}
