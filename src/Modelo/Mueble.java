package Modelo;

//import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Date;

public class Mueble extends Producto {
    
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");

    public enum Madera {
        PINO, ROBLE, HAYA
    };

    //private Date anyoFab;
    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }

    @Override
    public String imprimirProducto() {
        
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yy");
        String res = super.imprimirProducto() + "el año de fabricación: " + sdf.format(anyoFab) + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    @Override
    public void setPrecio(double precioBase) {
        switch (this.getTipoMadera()) {
            case ROBLE:
                super.precio = precioBase + precioBase * 0.1;
                break;
            case PINO:
                super.precio = precioBase - precioBase * 0.1;
                break;
            default:
                super.precio = precioBase;
                break;
        }
    }

    public String getAnyoFab() {
        return sdf.format(anyoFab);
    }

    public void setAnyoFab(LocalDate anyoFab) {
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
