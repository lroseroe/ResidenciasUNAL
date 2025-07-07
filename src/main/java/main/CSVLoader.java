package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JTable;


public class CSVLoader {
    public static JTable loadCSV(InputStream file){
        try{
            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader reader = new BufferedReader(isr);
            
            ArrayList<String[]> rows = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                String[] row = line.split(";");
                rows.add(row);
            }
            
            //Pasar a un array de Objetos
            String[] titles = rows.get(0);
            Object[][] data = new Object[rows.size() - 1][titles.length];
            for(int i = 1; i < rows.size(); i++){
                String[] rowArray = rows.get(i);
                for(int j = 0; j < titles.length; j++){
                    if(j < rowArray.length){
                        data[i - 1][j] = rowArray[j];
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
            
            
            return new JTable(data, titles);
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
