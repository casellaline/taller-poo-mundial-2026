//A

public class CuerpoTecnico extends Persona{
    private Rol rol;
    //Constructor

    public CuerpoTecnico(){}

    public CuerpoTecnico(String nombre, int fecNacimiento, Rol rol){
        super(nombre, fecNacimiento);
        this.rol=rol;
    }
    //Get & Set

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
