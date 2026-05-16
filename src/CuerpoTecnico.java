public class CuerpoTecnico extends Persona{
    private Rol rol;
    //Constructor
    public CuerpoTecnico(String nombre, int fecNacimiento, Rol rol){
        super(nombre, fecNacimiento);
        this.rol=rol;
    }

    public Rol getRol() {
        return rol;
    }
}
