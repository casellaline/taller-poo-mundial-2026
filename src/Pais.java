public class Pais {
    private String nombre;
    private String bandera;
    private Sede perteneceSede;
    private Arbitro cuentaConArbitro;
    private Seleccion representaSeleccion;
    //Constructor
    public Pais(String nombre, String bandera){
        this.nombre=nombre;
        this.bandera=bandera;
        this.perteneceSede=null;
        this.cuentaConArbitro=null;
        this.representaSeleccion=null;
    }
    public Pais(String nombre, String bandera, Sede perteneceSede, Arbitro cuentaConArbitro, Seleccion representaSeleccion){
        this.nombre=nombre;
        this.bandera=bandera;
        this.perteneceSede=perteneceSede;
        this.cuentaConArbitro=cuentaConArbitro;
        this.representaSeleccion=representaSeleccion;
    }
    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public String getBandera() {
        return bandera;
    }

    public Sede getPerteneceSede() {
        return perteneceSede;
    }

    public void asociarSede(Sede sede) {
        this.perteneceSede = sede;
    }

    public Arbitro getCuentaConArbitro() {
        return cuentaConArbitro;
    }

    public void asociarArbitro(Arbitro arbitro) {
        this.cuentaConArbitro = arbitro;
    }

    public Seleccion getRepresentaSeleccion() {
        return representaSeleccion;
    }

    public void asociarSeleccion(Seleccion seleccion) {
        this.representaSeleccion = seleccion;
    }
}
