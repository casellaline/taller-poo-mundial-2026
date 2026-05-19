import java.util.ArrayList;
import java.util.List;

public class Mundial {
    private int anio;
    private String mascota;
    private int fechaDesde;
    private int fechasHasta;
    private List<Sede> involucraSede;
    //Constructores
    public Mundial(){
        this.involucraSede=new ArrayList<Sede>();
    }
    public Mundial(int anio, String mascota, int fechaDesde, int fechasHasta, List<Sede> involucraSede) {
        this.anio = anio;
        this.mascota = mascota;
        this.fechaDesde = fechaDesde;
        this.fechasHasta = fechasHasta;
        this.involucraSede=involucraSede;
    }
    //Getters y Setters

    public void asociarSede(Sede sede) {
        this.involucraSede.add(sede);
    }

    public int getAnio() {
        return anio;
    }

    public String getMascota() {
        return mascota;
    }

    public int getFechaDesde() {
        return fechaDesde;
    }

    public int getFechasHasta() {
        return fechasHasta;
    }
}
