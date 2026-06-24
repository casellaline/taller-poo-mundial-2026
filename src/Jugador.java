import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un jugador de futbol perteneciente a una unica seleccion
 * nacional. Mantiene sus datos deportivos (dorsal, posicion, peso, altura)
 * y la lista de eventos en los que participo.
 */
public class Jugador extends Persona {

    private int dorsal;
    private Posicion posicion;
    private float peso;
    private float altura;
    private Seleccion seleccion;
    private List<Evento> eventos;

    // Constructor parametrizado
    /**
     * Crea una instancia de {@code Jugador} con los datos indicados.
     *
     * @param nombre nombre
     * @param fecNacimiento fecNacimiento
     * @param dorsal dorsal
     * @param posicion posicion
     * @param peso peso
     * @param altura altura
     * @param seleccion seleccion
     */
    public Jugador(String nombre, int fecNacimiento, int dorsal,
                   Posicion posicion, float peso, float altura, Seleccion seleccion) {
        super(nombre, fecNacimiento);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.peso = peso;
        this.altura = altura;
        this.seleccion = seleccion;
        this.eventos = new ArrayList<Evento>();
    }

    // Constructor por defecto
    /**
     * Crea una instancia vacia de {@code Jugador}.
     */
    public Jugador() {
        super(); // Buena práctica llamar al padre
        this.eventos = new ArrayList<Evento>();
    }

    // Getters y Setters
    /**
     * Devuelve dorsal.
     * @return dorsal
     */
    public int getDorsal() { return dorsal; }

    /**
     * Establece dorsal.
     *
     * @param dorsal dorsal
     */
    public void setDorsal(int dorsal) { this.dorsal = dorsal; }

    /**
     * Devuelve posicion.
     * @return posicion
     */
    public Posicion getPosicion() { return posicion; }

    /**
     * Establece posicion.
     *
     * @param posicion posicion
     */
    public void setPosicion(Posicion posicion) { this.posicion = posicion; }

    /**
     * Devuelve peso.
     * @return peso
     */
    public float getPeso() { return peso; }

    /**
     * Establece peso.
     *
     * @param peso peso
     */
    public void setPeso(float peso) { this.peso = peso; }

    /**
     * Devuelve altura.
     * @return altura
     */
    public float getAltura() { return altura; }

    /**
     * Establece altura.
     *
     * @param altura altura
     */
    public void setAltura(float altura) { this.altura = altura; }

    /**
     * Devuelve seleccion.
     * @return seleccion
     */
    public Seleccion getSeleccion() { return seleccion; }

    /**
     * Establece seleccion.
     *
     * @param seleccion seleccion
     */
    public void setSeleccion(Seleccion seleccion) { this.seleccion = seleccion; }

    /**
     * Devuelve eventos.
     * @return eventos
     */
    public List<Evento> getEventos() { return eventos; }

    /**
     * Establece eventos.
     *
     * @param eventos eventos
     */
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; }

    // Método para agregar un evento a la lista
    /**
     * Agrega un evento al jugador y establece la relacion inversa.
     *
     * @param evento evento
     */
    public void agregarEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setJugador(this);
    }
}