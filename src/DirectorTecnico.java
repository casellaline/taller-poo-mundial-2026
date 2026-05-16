public class DirectorTecnico extends Persona{
    private int fechaNombramiento;
    //Constructor
    public DirectorTecnico(String nombre, int fecNacimiento, int fechaNombramiento){
        super(nombre, fecNacimiento);
        this.fechaNombramiento=fechaNombramiento;
    }
    //Getters
    public int getFechaNombramiento() {
        return fechaNombramiento;
    }
}



