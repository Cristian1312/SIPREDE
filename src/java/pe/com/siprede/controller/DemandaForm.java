/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.encog.Encog;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import pe.com.siprede.model.Demanda;
import pe.com.siprede.util.Normalizacion;

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
        FacesContext ctx = FacesContext.getCurrentInstance();
        String relativeWebPath = "/resources/csv/";
        ServletContext servletContext = (ServletContext) ctx.getExternalContext().getContext();
        String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
        
        // Inicializar la red neuronal
        this.predictorBean.crearPredictor(9, 2, 1);
        // Normalizar patrones de entrenamiento desde un archivo .csv
        Normalizacion normEntrenamiento = new Normalizacion();
        normEntrenamiento.normalizarDataArchivoCSV(absoluteDiskPath + "\\data_entrenamiento.csv",
                absoluteDiskPath + "\\data_entrenamiento_norm.csv");
        // Crear el conjunto de data de entrenamiento
        MLDataSet conjuntoEntrenamiento = this.predictorBean.crearConjuntoDataEntrenamiento(
                normEntrenamiento.getArchivoDestino().getAbsolutePath(), false);
        // Entrenar la red neuronal mediante el algoritmo BP
        Backpropagation bp = this.predictorBean.entrenarRedNeuronal(conjuntoEntrenamiento, 0.5, 0.25, 0.02);
        // Normalizar patrones de validacion desde un archivo .csv
        Normalizacion normValidacion = new Normalizacion();
        normValidacion.normalizarDataArchivoCSV(absoluteDiskPath + "\\data_validacion.csv",
                absoluteDiskPath + "\\data_validacion_norm.csv");
        // Crear el conjunto de data de validacion
        MLDataSet conjuntoValidacion = this.predictorBean.crearConjuntoDataValidacion(
                normValidacion.getArchivoDestino().getAbsolutePath(), false);
        // Validar la red neuronal
        this.predictorBean.validarRedNeuronal(conjuntoValidacion);
        
        double[] inputData = {0.09090909090909091,0.24781341107871713,0,0,1,0.14473684210526283,0,0,1};
        //conjuntoValidacion.add(null);
        
        System.out.println(conjuntoValidacion.getInputSize());
        System.out.println(this.predictorBean.getPredictor().getPerceptronML().calculateError(conjuntoValidacion));
        Encog.getInstance().shutdown();
    }
}
