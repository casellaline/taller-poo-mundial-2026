public class Jugador extends Persona{
    private int dorsal;
    private Posicion posicion;
    private float peso;
    private float altura;
    private Seleccion integraSeleccion;
    private Evento involucraEvento;
    // constructor
    public Jugador(String nombre, int fecNacimiento, int dorsal, Posicion posicion, float peso, float altura, Seleccion integraSeleccion){
        super(nombre, fecNacimiento);
        this.dorsal=dorsal;
        this.posicion=posicion;
        this.peso=peso;
        this.altura=altura;
        this.integraSeleccion=integraSeleccion;
    }
    //Getters y Setters

    public void setInvolucraEvento(Evento involucraEvento) {
        this.involucraEvento = involucraEvento;
    }

    public int getDorsal() {
        return dorsal;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public float getPeso() {
        return peso;
    }

    public float getAltura() {
        return altura;
    }

    public Seleccion getIntegraSeleccion() {
        return integraSeleccion;
    }

    public Evento getInvolucraEvento() {
        return involucraEvento;
    }
}
