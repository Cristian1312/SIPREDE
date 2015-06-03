/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.encog.neural.networks.BasicNetwork;

/**
 *
 * @author Cristian1312
 */
@ManagedBean
@RequestScoped
public class Predictor {
    private BasicNetwork perceptronML;
    private double errorDeseado;
    /**
     * Creates a new instance of Predictor
     */
    public Predictor() {}

    public BasicNetwork getPerceptronML() {
        return perceptronML;
    }

    public void setPerceptronML() {
        this.perceptronML = new BasicNetwork();
    }

    public double getErrorDeseado() {
        return errorDeseado;
    }

    public void setErrorDeseado(double errorDeseado) {
        this.errorDeseado = errorDeseado;
    }
}
