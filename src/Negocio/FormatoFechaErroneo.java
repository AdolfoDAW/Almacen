/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import java.time.LocalDate;

/**
 *
 * @author daw
 */
public class FormatoFechaErroneo extends RuntimeException {

    public FormatoFechaErroneo(LocalDate fecha) {
        super("\nFecha " + fecha + " incorrecta, ej: 02/enero/2015, se introducirá la fecha de hoy");
    }
}
