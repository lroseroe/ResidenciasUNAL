package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JTable;
import residenciasunalhash.CriptoCode;
import residenciasunalhash.Estudiante;


public class CSVLoader {
    MainPanel panel;
    
    public CSVLoader(MainPanel panel){
        this.panel = panel;
    }
    
    public static JTable loadCSV(InputStream file){
        try{
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(isr);
            
            ArrayList<String[]> rows = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                String id = CriptoCode.desencriptar(row[0]);
                String nombre = CriptoCode.desencriptar(row[1]).replace(";", ",");
                String puntaje = CriptoCode.desencriptar(row[2]);
                String apoyo = CriptoCode.desencriptar(row[3]);
                
                String[] rowDesencriptada = {nombre, id, puntaje, apoyo};
                
                rows.add(rowDesencriptada);
            }
            
            String[] titles = {"Nombre", "ID", "Puntaje", "Residencia"};
            
            //Pasar a un array de Objetos
            String[][] data = new String[rows.size()][titles.length];
            for(int i = 0; i < rows.size(); i++){
                String[] rowArray = rows.get(i);
                for(int j = 0; j < titles.length; j++){
                    if(j < rowArray.length){
                        data[i][j] = rowArray[j];
                    } else {
                        data[i][j] = "";
                    }
                }
            }
            
            
            return new JTable(data, titles);
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public Object[][] sortCSV(String[][] data){
        int m = data.length;
        int n = data[0].length;
        Object sortedData[][] = new Object[m][n];
        for(int i = 0; i < m; i++){
            String nombre = data[i][0];
            long id = Long.parseLong(data[i][1]);
            int puntaje = Integer.parseInt(data[i][2]);
            boolean apoyo = Boolean.parseBoolean(data[i][3]);
            
            Estudiante estudiante = new Estudiante(id, nombre, puntaje);
            estudiante.setApoyo(apoyo);
            
            panel.control.heap.insert(estudiante);
        }

        return sortedData;
    }
    
}
