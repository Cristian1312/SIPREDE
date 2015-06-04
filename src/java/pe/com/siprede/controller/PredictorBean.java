/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.TrainingSetUtil;
import pe.com.siprede.model.Predictor;
import pe.com.siprede.util.Numero;

/**
 *
 * @author Cristian1312
 */
@ManagedBean
@RequestScoped
public class PredictorBean {

    /**
     * Creates a new instance of PredictorBean
     */
    public PredictorBean() {}
    
    @ManagedProperty(value = "#{predictor}")
    private Predictor predictor;

    /**
     * @return the predictor
     */
    public Predictor getPredictor() {
        return predictor;
    }

    /**
     * @param predictor the predictor to set
     */
    public void setPredictor(Predictor predictor) {
        this.predictor = predictor;
    }
    
    public void crearPredictor(int nroNeuronasCapaEntrada, int nroNeuronasCapaOculta,
            int nroNeuronasCapaSalida) {
        this.predictor.setPerceptronML();
        this.predictor.getPerceptronML().addLayer(new BasicLayer(null, true, nroNeuronasCapaEntrada));
        this.predictor.getPerceptronML().addLayer(new BasicLayer(new ActivationSigmoid(), true, nroNeuronasCapaOculta));
        this.predictor.getPerceptronML().addLayer(new BasicLayer(new ActivationSigmoid(), false, nroNeuronasCapaSalida));
        this.predictor.getPerceptronML().getStructure().finalizeStructure();
        this.predictor.getPerceptronML().reset(500);
    }
    
    public MLDataSet crearConjuntoDataEntrenamiento(String rutaArchivo, boolean tieneHeaders) {
        int nroDeEntradas = this.predictor.getPerceptronML().getInputCount();
        int nroDeSalidas = this.predictor.getPerceptronML().getOutputCount();
        MLDataSet conjuntoDeEntrenamiento = TrainingSetUtil.loadCSVTOMemory(
                CSVFormat.ENGLISH, rutaArchivo, tieneHeaders,
                nroDeEntradas, nroDeSalidas);

        return conjuntoDeEntrenamiento;
    }

    public Backpropagation entrenarRedNeuronal(MLDataSet dataEntrenamiento, double tasaAprendizaje,
            double terminoMomento, double errorDeseado) {
        this.predictor.setErrorDeseado(errorDeseado);
        final Backpropagation bp = new Backpropagation(this.predictor.getPerceptronML(), dataEntrenamiento,
                tasaAprendizaje, terminoMomento);
        do {
            bp.iteration();
        } while (bp.getError() > errorDeseado);
        
        return bp;
    }
    
    public void mostrarPesos() {
        for (int i = 1; i < this.predictor.getPerceptronML().getLayerCount(); i++) {
            System.out.println("        Capa " + (i + 1) + ":");
            for (int j = 0; j < this.predictor.getPerceptronML().getLayerNeuronCount(i); j++) {
                System.out.println("            Neurona " + (j + 1) + ":");
                for (int k = 0; k < this.predictor.getPerceptronML().getLayerNeuronCount(i - 1); k++) {
                    System.out.println("                "
                            + "Peso(" + (j + 1) + "," + (k + 1) + "): " + Numero.
                                    redondear(this.predictor.getPerceptronML().getWeight(i - 1, k, j), 4));
                }
            }
        }
    }

    public MLDataSet crearConjuntoDataValidacion(String rutaArchivo, boolean tieneHeaders) {
        int nroDeEntradas = this.predictor.getPerceptronML().getInputCount();
        int nroDeSalidas = this.predictor.getPerceptronML().getOutputCount();
        MLDataSet conjuntoDeValidacion = TrainingSetUtil.loadCSVTOMemory(
                CSVFormat.ENGLISH, rutaArchivo, tieneHeaders,
                nroDeEntradas, nroDeSalidas);

        return conjuntoDeValidacion;
    }

    public void validarRedNeuronal(MLDataSet dataValidacion) {
        for (MLDataPair pair : dataValidacion) {
            final MLData output = this.predictor.getPerceptronML().compute(pair.getInput());
        }
    }
}