import java.util.ArrayList;
import java.util.List;

public class Jugador extends Persona{

    private int dorsal;
    private Posicion posicion;
    private float peso;
    private float altura;
    private Seleccion integraSeleccion;
    private List<Evento> involucraEvento;
    // constructor
    public void Jugador(){
        this.involucraEvento= new ArrayList<Evento>();
    }
    public Jugador(String nombre, int fecNacimiento, int dorsal, Posicion posicion, float peso, float altura, Seleccion integraSeleccion,List<Evento> involucraEvento){
        super(nombre, fecNacimiento);
        this.dorsal=dorsal;
        this.posicion=posicion;
        this.peso=peso;
        this.altura=altura;
        this.integraSeleccion=integraSeleccion;
        this.involucraEvento=involucraEvento;
    }

    //Getters y Setters

    public void agregarEvento(Evento evento) {
        this.involucraEvento.add(evento);
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
    //Mostrar evento
}
