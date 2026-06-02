import java.util.ArrayList;
import java.util.List;

public class Mundial {
    private int anio;
    private String mascota;
    private int fechaDesde;
    private int fechasHasta;
    private List<Sede> sedes;

    //Constructores

    public Mundial(){

    }

    public Mundial(int anio, String mascota, int fechaDesde, int fechasHasta) {
        this.anio = anio;
        this.mascota = mascota;
        this.fechaDesde = fechaDesde;
        this.fechasHasta = fechasHasta;
        this.sedes=new ArrayList<Sede>();
    }
    //Getters y Setters

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public int getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(int fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public int getFechasHasta() {
        return fechasHasta;
    }

    public void setFechasHasta(int fechasHasta) {
        this.fechasHasta = fechasHasta;
    }

    public List<Sede> getSedes() {
        return sedes;
    }

    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    public void asociarSede(Sede sede) {
        this.sedes.add(sede);
    }


}
