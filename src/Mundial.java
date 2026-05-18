public class Mundial {
    private int anio;
    private String mascota;
    private int fechaDesde;
    private int fechasHasta;
    private Sede involucraSede;
    //Constructores
    public Mundial(int anio, String mascota, int fechaDesde, int fechasHasta) {
        this.anio = anio;
        this.mascota = mascota;
        this.fechaDesde = fechaDesde;
        this.fechasHasta = fechasHasta;
    }

    public Mundial(int anio, String mascota, int fechaDesde, int fechasHasta, Sede involucraSede) {
        this.anio = anio;
        this.mascota = mascota;
        this.fechaDesde = fechaDesde;
        this.fechasHasta = fechasHasta;
        this.involucraSede=involucraSede;
    }
    //Getters y Setters

    public void asociarSede(Sede sede) {
        this.involucraSede = sede;
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

    public Sede getInvolucraSede() {
        return involucraSede;
    }
}
