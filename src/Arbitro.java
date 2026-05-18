public class Arbitro extends Persona{
    private int aniosExperiencia;
    //Constructor
    public Arbitro(String nombre, int fecNacimiento, int aniosExperiencia){
        super(nombre, fecNacimiento);
        this.aniosExperiencia=aniosExperiencia;
    }
    //Getters

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }
}
