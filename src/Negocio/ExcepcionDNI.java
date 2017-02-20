/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author daw
 */
public class ExcepcionDNI extends RuntimeException {

    public ExcepcionDNI(String dni) {
        super("DNI " + dni + " no válido");
    }

    /*public static void esValido(String dni) {
        if (dni.length() != 8 && dni.length() != 8) {
            throw new ExcepcionDNI(dni);
        }
    } */
}
