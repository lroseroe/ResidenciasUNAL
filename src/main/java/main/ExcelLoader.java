package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLoader {
    public static JTable loadExcel(InputStream file){
        try{
            Workbook wk = new XSSFWorkbook(file); //Es como el libro de excel
            
            Sheet sheet = wk.getSheetAt(0); //Obtener la primera hoja
            ArrayList<String[]> rows = new ArrayList<>();
            int maxCols = 4; //Nombre, ID ,puntaje y si tiene residencia o no
            
            for(Row row : sheet){
                ArrayList<String> rowData = new ArrayList<>(); //Sacar los datos de cada fila
                for(Cell cell : row){
                    cell.setCellType(CellType.STRING); //Todas las celdas se guardan como string
                    rowData.add(cell.getStringCellValue());
                }
                rows.add(rowData.toArray(new String[0]));
            }
            
            //Pasar a un array de Objetos
            Object[][] data = new Object[rows.size() - 1][maxCols]; //No se cuenta la primera fila ya que esos son los titulos
            for(int i = 1; i < rows.size(); i++){
                String[] rowArray = rows.get(i);
                for(int j = 0; j < maxCols; j++){
                    if(j < rowArray.length){
                        data[i - 1][j] = rowArray[j];
                    } else {
                        data[i - 1][j] = "";
                    }
                }
            }
            
            String[] titles = rows.get(0);
            return new JTable(data, titles);
            
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
