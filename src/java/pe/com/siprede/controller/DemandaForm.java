/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.controller;

import pe.com.siprede.bean.PredictorBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import pe.com.siprede.model.Demanda;
import pe.com.siprede.util.Mes;
import pe.com.siprede.util.Normalizacion;
import pe.com.siprede.util.Ruta;

/**
 *
 * @author Cristian1312
 */
@ManagedBean
@RequestScoped
public class DemandaForm {

    /**
     * Creates a new instance of DemandaForm
     */
    public DemandaForm() {
    }
    
    @ManagedProperty(value = "#{demanda}")
    private Demanda demanda;
    @ManagedProperty(value = "#{predictorBean}")
    private PredictorBean predictorBean;

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