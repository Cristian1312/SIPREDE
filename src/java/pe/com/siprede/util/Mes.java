/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.util;

/**
 *
 * @author Cristian1312
 */
public class Mes {
    public static String getNombreMes(String numeroMes) { 
        String[] nombresMeses = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo"
                ,"Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre"
                ,"Diciembre"};
        int nroMes = Integer.parseInt(numeroMes);
        
        return nombresMeses[nroMes];
    }
}
