/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Cristian1312
 */
@ManagedBean
@RequestScoped
public class Demanda {
    
    private String mes;
    private String precioProducto;
    private String promocion;
    private String tiempoPromocion;
    private String publicidad;
    private String precioProductoC;
    private String promocionC;
    private String tiempoPromocionC;
    private String publicidadC;
    private String cantidadDemandada;
    
    private String mensajeFinal;
    /**
     * Creates a new instance of Demanda
     */
    public Demanda() {
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the precioProducto
     */
    public String getPrecioProducto() {
        return precioProducto;
    }

    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * @return the promocion
     */
    public String getPromocion() {
        return promocion;
    }

    /**
     * @param promocion the promocion to set
     */
    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    /**
     * @return the tiempoPromocion
     */
    public String getTiempoPromocion() {
        return tiempoPromocion;
    }

    /**
     * @param tiempoPromocion the tiempoPromocion to set
     */
    public void setTiempoPromocion(String tiempoPromocion) {
        this.tiempoPromocion = tiempoPromocion;
    }

    /**
     * @return the publicidad
     */
    public String getPublicidad() {
        return publicidad;
    }

    /**
     * @param publicidad the publicidad to set
     */
    public void setPublicidad(String publicidad) {
        this.publicidad = publicidad;
    }

    /**
     * @return the precioProductoC
     */
    public String getPrecioProductoC() {
        return precioProductoC;
    }

    /**
     * @param precioProductoC the precioProductoC to set
     */
    public void setPrecioProductoC(String precioProductoC) {
        this.precioProductoC = precioProductoC;
    }

    /**
     * @return the promocionC
     */
    public String getPromocionC() {
        return promocionC;
    }

    /**
     * @param promocionC the promocionC to set
     */
    public void setPromocionC(String promocionC) {
        this.promocionC = promocionC;
    }

    /**
     * @return the tiempoPromocionC
     */
    public String getTiempoPromocionC() {
        return tiempoPromocionC;
    }

    /**
     * @param tiempoPromocionC the tiempoPromocionC to set
     */
    public void setTiempoPromocionC(String tiempoPromocionC) {
        this.tiempoPromocionC = tiempoPromocionC;
    }

    /**
     * @return the publicidadC
     */
    public String getPublicidadC() {
        return publicidadC;
    }

    /**
     * @param publicidadC the publicidadC to set
     */
    public void setPublicidadC(String publicidadC) {
        this.publicidadC = publicidadC;
    }

    /**
     * @return the cantidadDemandada
     */
    public String getCantidadDemandada() {
        return cantidadDemandada;
    }

    /**
     * @param cantidadDemandada the cantidadDemandada to set
     */
    public void setCantidadDemandada(String cantidadDemandada) {
        this.cantidadDemandada = cantidadDemandada;
    }

    /**
     * @return the mensajeFinal
     */
    public String getMensajeFinal() {
        return mensajeFinal;
    }

    /**
     * @param mensajeFinal the mensajeFinal to set
     */
    public void setMensajeFinal(String mensajeFinal) {
        this.mensajeFinal = mensajeFinal;
    }
}
