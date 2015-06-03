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
    
    private double mes;
    private double precioProducto;
    private double promocion;
    private double tiempoPromocion;
    private double publicidad;
    private double precioProductoC;
    private double promocionC;
    private double tiempoPromocionC;
    private double publicidadC;
    private double cantidadDemandada;
    /**
     * Creates a new instance of Demanda
     */
    public Demanda() {
    }

    /**
     * @return the mes
     */
    public double getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(double mes) {
        this.mes = mes;
    }

    /**
     * @return the precioProducto
     */
    public double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * @param precioProducto the precioProducto to set
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * @return the promocion
     */
    public double getPromocion() {
        return promocion;
    }

    /**
     * @param promocion the promocion to set
     */
    public void setPromocion(double promocion) {
        this.promocion = promocion;
    }

    /**
     * @return the tiempoPromocion
     */
    public double getTiempoPromocion() {
        return tiempoPromocion;
    }

    /**
     * @param tiempoPromocion the tiempoPromocion to set
     */
    public void setTiempoPromocion(double tiempoPromocion) {
        this.tiempoPromocion = tiempoPromocion;
    }

    /**
     * @return the publicidad
     */
    public double getPublicidad() {
        return publicidad;
    }

    /**
     * @param publicidad the publicidad to set
     */
    public void setPublicidad(double publicidad) {
        this.publicidad = publicidad;
    }

    /**
     * @return the precioProductoC
     */
    public double getPrecioProductoC() {
        return precioProductoC;
    }

    /**
     * @param precioProductoC the precioProductoC to set
     */
    public void setPrecioProductoC(double precioProductoC) {
        this.precioProductoC = precioProductoC;
    }

    /**
     * @return the promocionC
     */
    public double getPromocionC() {
        return promocionC;
    }

    /**
     * @param promocionC the promocionC to set
     */
    public void setPromocionC(double promocionC) {
        this.promocionC = promocionC;
    }

    /**
     * @return the tiempoPromocionC
     */
    public double getTiempoPromocionC() {
        return tiempoPromocionC;
    }

    /**
     * @param tiempoPromocionC the tiempoPromocionC to set
     */
    public void setTiempoPromocionC(double tiempoPromocionC) {
        this.tiempoPromocionC = tiempoPromocionC;
    }

    /**
     * @return the publicidadC
     */
    public double getPublicidadC() {
        return publicidadC;
    }

    /**
     * @param publicidadC the publicidadC to set
     */
    public void setPublicidadC(double publicidadC) {
        this.publicidadC = publicidadC;
    }

    /**
     * @return the cantidadDemandada
     */
    public double getCantidadDemandada() {
        return cantidadDemandada;
    }

    /**
     * @param cantidadDemandada the cantidadDemandada to set
     */
    public void setCantidadDemandada(double cantidadDemandada) {
        this.cantidadDemandada = cantidadDemandada;
    }
}
