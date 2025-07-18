package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    MainPanel panel;

    public Config(MainPanel panel) {
        this.panel = panel;
    }
    
    public void guardarConfig(){
        try {
            BufferedWriter guardado = new BufferedWriter(new  FileWriter("config.txt"));
            
            guardado.write(String.valueOf(panel.totalResidences));
            guardado.newLine();
            
            guardado.write(String.valueOf(panel.takenResidences));
            guardado.newLine();
            
            guardado.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cargarConfig(){
        try {
            BufferedReader lector = new BufferedReader(new FileReader("config.txt"));
            String linea = lector.readLine();          
            panel.totalResidences = Integer.parseInt(linea);
            
            linea = lector.readLine();
            panel.takenResidences = Integer.parseInt(linea); 
            
            lector.close();
            
            panel.availableResidences = panel.totalResidences - panel.takenResidences;

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
