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
public class Numero {

    public static double redondear(double numero, int digitos) {
        int cifras = (int) Math.pow(10, digitos);
        return Math.rint(numero * cifras) / cifras;
    }
    
    public static String desnormalizar(String valorNorm, double rmax, double rmin,
            double dmax, double dmin) {
        double vNorm = Double.parseDouble(valorNorm);
        int vDesnorm;
        
        vDesnorm = (int) (((vNorm - rmin)/(rmax - rmin)*(dmax - dmin)) + dmin);
        
        return String.valueOf(vDesnorm);
    }
    
    public static double normalizar(double valorDesnorm, double rmax, double rmin,
            double dmax, double dmin) {
        double valorNorm;
        
        valorNorm = (((valorDesnorm - dmin)/(dmax - dmin))* (rmax - rmin)) + rmin;
        
        return valorNorm;
    }
}
