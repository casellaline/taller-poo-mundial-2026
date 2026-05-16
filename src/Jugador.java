public class Jugador extends Persona{
    private int dorsal;
    private Posicion posicion;
    private float peso;
    private float altura;
    // constructor
    public Jugador(String nombre, int fecNacimiento, int dorsal, Posicion posicion, float peso, float altura){
        super(nombre, fecNacimiento);
        this.dorsal=dorsal;
        this.posicion=posicion;
        this.peso=peso;
        this.altura=altura;
    }
    //Getters

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
}
