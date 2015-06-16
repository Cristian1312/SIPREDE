/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.util.simple.TrainingSetUtil;
import pe.com.siprede.model.Demanda;
import pe.com.siprede.model.Predictor;
import pe.com.siprede.util.Mes;
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
    
    public void elaborarPrediccion(Demanda demanda) {
        FacesMessage msg = null;
        double[][] inputData = {
            {Double.parseDouble(demanda.getMes()), Double.parseDouble(demanda.getPrecioProducto()),
                Double.parseDouble(demanda.getPromocion()), Double.parseDouble(demanda.getTiempoPromocion()),
                Double.parseDouble(demanda.getPublicidad()), Double.parseDouble(demanda.getPrecioProductoC()),
                Double.parseDouble(demanda.getPromocionC()),  Double.parseDouble(demanda.getTiempoPromocionC()),
                Double.parseDouble(demanda.getPublicidadC())}
        };
        double[][] outputData = {{0}};
        if (formularioSeEnvia(inputData)) {
            MLDataSet conjuntoParaPredecir = new BasicMLDataSet(inputData, outputData);
            String cantidadDemandada = "";
            for(MLDataPair patron: conjuntoParaPredecir) {
                final MLData prediccion = this.predictor.getPerceptronML().compute(patron.getInput());
                cantidadDemandada = String.valueOf(prediccion.getData(0));
            }
            demanda.setCantidadDemandada(cantidadDemandada);
            String msgFinal = "La cantidad demandada para el mes de " +
                    Mes.getNombreMes(demanda.getMes()) + " será de " +
                    Numero.desnormalizar(demanda.getCantidadDemandada(), 1, 0, 11812, 3297) + 
                    " unidades.";
            msg = new FacesMessage("Exito!", msgFinal);
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Encog.getInstance().shutdown();
    }
    
    public boolean formularioSeEnvia(double[][] entradas) {
        boolean formularioSeEnvia = true;
        
        for (int i = 0; i < entradas[0].length; i++) {
            if (String.valueOf(entradas[0][i]) == null) {
                formularioSeEnvia = false;
                break;
            }
        }
        
        return formularioSeEnvia;
    }
}