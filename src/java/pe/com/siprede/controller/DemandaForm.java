/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import pe.com.siprede.bean.PredictorBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import pe.com.siprede.model.Demanda;
import pe.com.siprede.util.Normalizacion;
import pe.com.siprede.util.Ruta;

/**
 *
 * @author Cristian1312
 */
@ManagedBean
@RequestScoped
public class DemandaForm implements Serializable {

    /**
     * Creates a new instance of DemandaForm
     */
    public DemandaForm() {
    }
    
    @ManagedProperty(value = "#{demanda}")
    private Demanda demanda;
    @ManagedProperty(value = "#{predictorBean}")
    private PredictorBean predictorBean;
    private boolean disableSliderTiempoPromocion;
    private boolean disableSliderTiempoPromocionC;
    
    @PostConstruct
    public void init() {
        this.disableSliderTiempoPromocion = false;
        this.setDisableSliderTiempoPromocionC(false);
    }

    /**
     * @return the demanda
     */
    public Demanda getDemanda() {
        return demanda;
    }

    /**
     * @param demanda the demanda to set
     */
    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }

    /**
     * @return the predictorBean
     */
    public PredictorBean getPredictorBean() {
        return predictorBean;
    }

    /**
     * @param predictorBean the predictorBean to set
     */
    public void setPredictorBean(PredictorBean predictorBean) {
        this.predictorBean = predictorBean;
    }
    
    /**
     * @return the disableSliderTiempoPromocion
     */
    public boolean isDisableSliderTiempoPromocion() {
        return disableSliderTiempoPromocion;
    }

    /**
     * @param disableSliderTiempoPromocion the disableSliderTiempoPromocion to set
     */
    public void setDisableSliderTiempoPromocion(boolean disableSliderTiempoPromocion) {
        this.disableSliderTiempoPromocion = disableSliderTiempoPromocion;
    }
    /**
     * @return the disableSliderTiempoPromocionC
     */
    public boolean isDisableSliderTiempoPromocionC() {
        return disableSliderTiempoPromocionC;
    }

    /**
     * @param disableSliderTiempoPromocionC the disableSliderTiempoPromocionC to set
     */
    public void setDisableSliderTiempoPromocionC(boolean disableSliderTiempoPromocionC) {
        this.disableSliderTiempoPromocionC = disableSliderTiempoPromocionC;
    }
    
    public void onPromocionChange() {
        if (demanda.getPromocion().equals("0")) {
            demanda.setTiempoPromocion("0.0");
            setDisableSliderTiempoPromocion(true);
        }
    }
    
    public void onPromocionCompChange() {
        if (demanda.getPromocionC().equals("0")) {
            demanda.setTiempoPromocionC("0.0");
            setDisableSliderTiempoPromocionC(true);
        }
    }
    
    public void predecir() {
        String rutaAbsoluta = Ruta.getRuta();
        // Inicializar la red neuronal
        this.predictorBean.crearPredictor(9, 2, 1);
        // Normalizar patrones de entrenamiento desde un archivo .csv
        Normalizacion normEntrenamiento = new Normalizacion();
        normEntrenamiento.normalizarDataArchivoCSV(rutaAbsoluta + "\\data_entrenamiento.csv",
                rutaAbsoluta + "\\data_entrenamiento_norm.csv");
        // Crear el conjunto de data de entrenamiento
        MLDataSet conjuntoEntrenamiento = this.predictorBean.crearConjuntoDataEntrenamiento(
                normEntrenamiento.getArchivoDestino().getAbsolutePath(), false);
        // Entrenar la red neuronal mediante el algoritmo BP
        Backpropagation bp = this.predictorBean.entrenarRedNeuronal(conjuntoEntrenamiento, 0.5, 0.25, 0.02);
        // Normalizar patrones de validacion desde un archivo .csv
        Normalizacion normValidacion = new Normalizacion();
        normValidacion.normalizarDataArchivoCSV(rutaAbsoluta + "\\data_validacion.csv",
                rutaAbsoluta + "\\data_validacion_norm.csv");
        // Crear el conjunto de data de validacion
        MLDataSet conjuntoValidacion = this.predictorBean.crearConjuntoDataValidacion(
                normValidacion.getArchivoDestino().getAbsolutePath(), false);
        // Validar la red neuronal
        this.predictorBean.validarRedNeuronal(conjuntoValidacion);
        // Prediccion final
        predictorBean.elaborarPrediccion(demanda);
    }
}