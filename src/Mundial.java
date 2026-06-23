import java.util.ArrayList;
import java.util.List;
/**
 * Representa la edicion del Mundial, con su anio, mascota, fechas de
 * inicio y fin, y las sedes asociadas.
 */
public class Mundial {
    private int anio;
    private String mascota;
    private int fechaDesde;
    private int fechaHasta;
    private List<Sede> sedes;

    //Constructores

    /**
     * Crea una instancia vacia de {@code Mundial}.
     */
    public Mundial(){
        this.sedes=new ArrayList<Sede>();
    }

    /**
     * Crea una instancia de {@code Mundial} con los datos indicados.
     *
     * @param anio anio
     * @param mascota mascota
     * @param fechaDesde fechaDesde
     * @param fechasHasta fechasHasta
     */
    public Mundial(int anio, String mascota, int fechaDesde, int fechasHasta) {
        this.anio = anio;
        this.mascota = mascota;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechasHasta;
        this.sedes=new ArrayList<Sede>();
    }
    //Getters y Setters

    /**
     * Devuelve anio.
     * @return anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece anio.
     *
     * @param anio anio
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Devuelve mascota.
     * @return mascota
     */
    public String getMascota() {
        return mascota;
    }

    /**
     * Establece mascota.
     *
     * @param mascota mascota
     */
    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    /**
     * Devuelve fecha desde.
     * @return fecha desde
     */
    public int getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Establece fecha desde.
     *
     * @param fechaDesde fechaDesde
     */
    public void setFechaDesde(int fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Devuelve fechas hasta.
     * @return fechas hasta
     */
    public int getFechasHasta() {
        return fechaHasta;
    }

    /**
     * Establece fechas hasta.
     *
     * @param fechasHasta fechasHasta
     */
    public void setFechasHasta(int fechasHasta) {
        this.fechaHasta = fechasHasta;
    }

    /**
     * Devuelve sedes.
     * @return sedes
     */
    public List<Sede> getSedes() {
        return sedes;
    }

    /**
     * Establece sedes.
     *
     * @param sedes sedes
     */
    public void setSedes(List<Sede> sedes) {
        this.sedes = sedes;
    }

    /**
     * Asocia una sede al Mundial.
     *
     * @param sede sede
     */
    public void asociarSede(Sede sede) {
        this.sedes.add(sede);
    }


}
