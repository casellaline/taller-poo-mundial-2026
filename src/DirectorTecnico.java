//A

public class DirectorTecnico extends Persona{
    private int fechaNombramiento;
    private Seleccion seleccion;
    //Constructor
    public DirectorTecnico(){}

    public DirectorTecnico(String nombre, int fecNacimiento, int fechaNombramiento){
        super(nombre, fecNacimiento);
        this.fechaNombramiento=fechaNombramiento;
    }

    public int getFechaNombramiento() {
        return fechaNombramiento;
    }

    public void setFechaNombramiento(int fechaNombramiento) {
        this.fechaNombramiento = fechaNombramiento;
    }

    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }
}