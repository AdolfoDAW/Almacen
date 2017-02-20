package Modelo;

public class Televisor extends Electrodomestico {

    public enum TipoTV {
        PLASMA, LED, LCD, OLED
    };

    protected double pulgadas;
    protected TipoTV tipo;

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        if (this.getTipo() == TipoTV.PLASMA && this.getPulgadas() >= 40) {
            super.precio = precioBase;
        } else if (this.getTipo() == TipoTV.PLASMA) {
            super.precio = precioBase - (precioBase * 0.1);
        } else if (this.getPulgadas() >= 40) {
            super.precio = precioBase + (precioBase * 0.1);
        }
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipo + "con " + this.pulgadas + " pulgadas";
        return res;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public TipoTV getTipo() {
        return tipo;
    }

    public void setTipo(TipoTV tipo) {
        this.tipo = tipo;
    }

}
