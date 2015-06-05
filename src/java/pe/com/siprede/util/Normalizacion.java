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

    public Normalizacion() {}

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
    
    public void normalizarDataArchivoCSV(String rutaOrigen, String rutaDestino) {
        try {
            archivoOrigen = new File(rutaOrigen);

            if (!archivoOrigen.exists()) {
                System.out.println("Archivo no existe!!!");
            }
            
            // Definir el formato del archivo .csv
            DataNormalization normalize = new DataNormalization();
            InputField inputM, inputPP, inputP, inputTP, inputPU, inputPPC,
                    inputPC, inputTPC, inputPUC, inputY;
            
            normalize.addInputField(inputM = new InputFieldCSV(true, archivoOrigen, 0));
            normalize.addInputField(inputPP = new InputFieldCSV(true, archivoOrigen, 1));
            normalize.addInputField(inputP = new InputFieldCSV(true, archivoOrigen, 2));
            normalize.addInputField(inputTP = new InputFieldCSV(true, archivoOrigen, 3));
            normalize.addInputField(inputPU = new InputFieldCSV(true, archivoOrigen, 4));
            normalize.addInputField(inputPPC = new InputFieldCSV(true, archivoOrigen, 5));
            normalize.addInputField(inputPC = new InputFieldCSV(true, archivoOrigen, 6));
            normalize.addInputField(inputTPC = new InputFieldCSV(true, archivoOrigen, 7));
            normalize.addInputField(inputPUC = new InputFieldCSV(true, archivoOrigen, 8));
            normalize.addInputField(inputY = new InputFieldCSV(false, archivoOrigen, 9));
            
            // Definir cuales variables se deberian normalizar
            normalize.addOutputField(new OutputFieldRangeMapped(inputM, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputPP, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputP, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputTP, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputPU, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputPPC, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputPC, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputTPC, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputPUC, 0, 1));
            normalize.addOutputField(new OutputFieldRangeMapped(inputY, 0, 1));
            
            // Definir a que ruta deberia guardarse el archivo con la data normalizada
            archivoDestino = new File(rutaDestino);
            normalize.setCSVFormat(CSVFormat.ENGLISH);
            normalize.setTarget(new NormalizationStorageCSV(CSVFormat.ENGLISH, archivoDestino));
            
            //Proceso
            normalize.process();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
