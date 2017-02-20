package Modelo;

public class Lavadora extends Electrodomestico {

    int revoluciones;
    double carga;

    public Lavadora() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        if (this.getRevoluciones() >= 500 && this.getCarga() < 8) {
            super.precio = precioBase - precioBase * 0.05;
        } else if (this.getRevoluciones() >= 500) {
            super.precio = precioBase + precioBase * 0.1;
        } else if (this.getCarga() < 8) {
            super.precio = precioBase - precioBase * 0.15;
        } else {
            super.precio = precioBase;
        }

    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "de revoluciones: " + this.revoluciones + "con carga: " + this.carga;
        return res;
    }
}
