/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.siprede.util;

import java.io.File;
import org.encog.util.csv.CSVFormat;
import org.encog.util.normalize.DataNormalization;
import org.encog.util.normalize.input.InputField;
import org.encog.util.normalize.input.InputFieldCSV;
import org.encog.util.normalize.output.OutputFieldRangeMapped;
import org.encog.util.normalize.target.NormalizationStorageCSV;

/**
 *
 * @author Cristian1312
 */
public class Normalizacion {
    private File archivoOrigen;
    private File archivoDestino;
    private InputField[] patrones;
    private final DataNormalization normalize;

    public Normalizacion() {
        normalize = new DataNormalization();
    }

    public File getArchivoOrigen() {
        return archivoOrigen;
    }

    public void setArchivoOrigen(File archivoOrigen) {
        this.archivoOrigen = archivoOrigen;
    }

    public File getArchivoDestino() {
        return archivoDestino;
    }

    public void setArchivoDestino(File archivoDestino) {
        this.archivoDestino = archivoDestino;
    }
    
    public InputField[] getPatrones() {
        return patrones;
    }

    public void setPatrones(InputField[] patrones) {
        this.patrones = patrones;
    }
    
    public void inicializarData() {
        InputField inputM, inputPP, inputP, inputTP, inputPU, inputPPC, 
                inputPC, inputTPC, inputPUC, inputY;
        inputM = new InputFieldCSV(true, archivoOrigen, 0);
        inputPP = new InputFieldCSV(true, archivoOrigen, 1);
        inputP = new InputFieldCSV(true, archivoOrigen, 2);
        inputTP = new InputFieldCSV(true, archivoOrigen, 3);
        inputPU = new InputFieldCSV(true, archivoOrigen, 4);
        inputPPC = new InputFieldCSV(true, archivoOrigen, 5);
        inputPC = new InputFieldCSV(true, archivoOrigen, 6);
        inputTPC = new InputFieldCSV(true, archivoOrigen, 7);
        inputPUC = new InputFieldCSV(true, archivoOrigen, 8);
        inputY = new InputFieldCSV(false, archivoOrigen, 9);
        
        patrones = new InputField[]{inputM, inputPP, inputP, inputTP, inputPU,
            inputPPC, inputPC, inputTPC, inputPUC, inputY};
    }
    
    public void agregarDataParaNormalizar() {
        for (InputField patron : patrones) {
            normalize.addInputField(patron);
        }
    }
    
    public void normalizarData() {
        for (InputField patron : patrones) {
            normalize.addOutputField(new OutputFieldRangeMapped(patron, 0, 1));
        }
    }
    
    public void normalizarDataArchivoCSV(String rutaOrigen, String rutaDestino) {
        try {
            archivoOrigen = new File(rutaOrigen);

            if (!archivoOrigen.exists()) {
                System.out.println("Archivo no existe!!!");
            } else {
                // Definir el formato del archivo .csv
                inicializarData();
                // Definir cuales variables se deberian normalizar
                agregarDataParaNormalizar();
                // Normalizacion de la data
                normalizarData();
                // Definir a que ruta deberia guardarse el archivo con la data normalizada
                archivoDestino = new File(rutaDestino);
                normalize.setCSVFormat(CSVFormat.ENGLISH);
                normalize.setTarget(new NormalizationStorageCSV(CSVFormat.ENGLISH,
                        archivoDestino));
                //Proceso
                normalize.process();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
