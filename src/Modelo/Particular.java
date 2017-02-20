package Modelo;

import Negocio.ExcepcionDNI;

public class Particular extends Cliente {

    private String dni;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean calcularDni(String dni) {
        boolean resultado = false;
        String[] letrasDNI = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
        if (dni.length() != 8 && dni.length() != 9) {
            throw new ExcepcionDNI(dni);
        } else {
            try {
                if (dni.length() == 8) {
                    int convertir = Integer.parseInt(dni);
                    String letra = (letrasDNI[convertir % 23]);
                    this.dni = dni + letra;
                    resultado = true;
                }
                if (dni.length() == 9) {
                    int convertir = Integer.parseInt(dni.substring(0, 8));
                    String letra = (letrasDNI[convertir % 23]);
                    String verificar = convertir + letra;
                    if (verificar.equals(dni)) {
                        this.dni = dni;
                        resultado = true;
                    } else {
                        throw new ExcepcionDNI(dni);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato incorrecto" + dni);
            }
        }
        return resultado;
    }

    /*public Particular(String nombre, String razonSocial,String dni) {
        super();
        this.dni = dni;
    }*/
    @Override
    public String imprimir() {
        String res = super.imprimir() + " DNI: " + this.dni;
        return res;
    }

}
