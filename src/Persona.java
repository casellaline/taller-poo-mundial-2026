public abstract class Persona {
    private String nombre;
    private int fecNacimiento;
    //Constructor
    public Persona(String nombre, int fecNacimiento){
        this.nombre=nombre;
        this.fecNacimiento=fecNacimiento;
    }
    //getters
    public String getNombre() {
        return nombre;
    }

    public int getFecNacimiento() {
        return fecNacimiento;
    }
}
