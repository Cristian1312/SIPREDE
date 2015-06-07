/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Cristian1312
 */
public class Ruta {
    public static String getRuta() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String relativeWebPath = "/resources/csv/";
        ServletContext servletContext = (ServletContext) ctx.getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        
        return absoluteDiskPath;
    }
}
