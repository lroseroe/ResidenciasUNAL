package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MouseController implements MouseListener{
    MainPanel panel;
    //booleanos para verificar que boton se ha presionado y saber que mostrar en UI
    public boolean searchBtnPressed = false, removeStudentBtnPressed = false, editScoreBtnPressed = false;
    public boolean editNumPlacesBtnPressed = false, checkAvailabiltyBtnPressed = false, listStudentsBtnPressed = false; 
    public boolean addStudentBtnPressed = false; 
    
    public boolean studentFound; //Solo para cuando estas buscando un estudiante o agregandolo
    
    public MouseController(MainPanel panel){
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object btn = e.getSource();
        
        if(panel.currentScreen == panel.studentInfoScreen){
            if(btn == panel.interfaz.searchBtn){
                searchBtnPressed = true;
                String IDstr = panel.interfaz.searchBox.getText();
                if((IDstr == null || !IDstr.matches("-?\\d+(\\.\\d+)?") || Long.parseLong(IDstr) <= 0) || 
                        Long.parseLong(IDstr) >= 100000000000L){ //Verificar string válido
                    JOptionPane.showMessageDialog(panel, "Ingrese un ID válido", "Error. Entrada no válida", JOptionPane.ERROR_MESSAGE);
                    return;
                } else { 
                    long ID = Long.parseLong(IDstr);
                    //Cambiar el puntaje
                }
                
                //Logica de buscar al estudiante pasandole ID
                
                studentFound = true;
            } else if(btn == panel.interfaz.removeStudentBtn){
                int choice = JOptionPane.showConfirmDialog(panel, "¿Está seguro?", "Eliminar estudiante", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(choice == JOptionPane.YES_OPTION){
                    //Eliminar estudiante
                } 
                
                //Si no, solo se cierra la ventanita
            } else if(btn == panel.interfaz.editScoreBtn){
                String newScoreStr = JOptionPane.showInputDialog(panel, "Ingrese el nuevo puntaje socioeconómico", 
                        "Editar puntaje", JOptionPane.INFORMATION_MESSAGE);
                
                if(newScoreStr != null && newScoreStr.matches("-?\\d+(\\.\\d+)?") && 
                        Integer.parseInt(newScoreStr) >= 0 ){ //Verificar que el string sea un número válido
                    int newScore = Integer.parseInt(newScoreStr);
                    //Cambiar el puntaje
                } else {
                    JOptionPane.showMessageDialog(panel, "Solo ingrese números positivos", "Error. Entrada no válida", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
        } else if(panel.currentScreen == panel.residenceAvailabilityScreen){
            if(btn == panel.interfaz.checkAvailabiltyBtn){
                checkAvailabiltyBtnPressed = true;
            } else if(btn == panel.interfaz.editNumPlacesBtn){
                editNumPlacesBtnPressed = true;
                
                String newNumPlacesStr = JOptionPane.showInputDialog(panel, "Ingrese la nueva cantidad de cupos", 
                        "Editar total de cupos", JOptionPane.INFORMATION_MESSAGE);
                if(newNumPlacesStr != null && newNumPlacesStr.matches("-?\\d+(\\.\\d+)?") && 
                        Integer.parseInt(newNumPlacesStr) >= 0 ){ //Verificar que el string sea un número válido
                    int newNumPlaces = Integer.parseInt(newNumPlacesStr);
                    panel.totalResidences = newNumPlaces;
                    panel.availableResidences = panel.totalResidences - panel.takenResidences; //Actualizar variable
                    
                    //También se debería actualizar la asignación de cupos con el heap
 
                } else {
                    JOptionPane.showMessageDialog(panel, "Solo ingrese números positivos", "Error. Entrada no válida", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } 
        } else if(panel.currentScreen == panel.asignationInfoScreen){
            if(btn == panel.interfaz.listStudentsBtn){
                listStudentsBtnPressed = true;
            }
        } else if(panel.currentScreen == panel.editAsignationScreen){
            if(btn == panel.interfaz.addStudentBtn){
                addStudentBtnPressed = true;
                
                //Verificar que los datos ingresados sean válidos
                    //Verficar si alguna caja de texto está vacía
                if(checkTextFieldEmpty(panel.interfaz.studentNamesBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentLastNamesBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentIDBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentScoreBox)){
                    JOptionPane.showMessageDialog(panel, "Asegúrese de llenar todos los campos", 
                            "Error: Panel vacío", JOptionPane.ERROR_MESSAGE);
                    //return para no actualizar componentes aún
                    return;
                } else {
                    String names = panel.interfaz.studentNamesBox.getText();
                    String lastNames = panel.interfaz.studentLastNamesBox.getText();
                    String ID_str = panel.interfaz.studentIDBox.getText();
                    String score_str = panel.interfaz.studentScoreBox.getText();
                    
                    if(ID_str.matches("-?\\d+(\\.\\d+)?") && Long.parseLong(ID_str) > 0){
                        Long ID = Long.parseLong(ID_str);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Ingrese un ID válido", 
                            "Error: Entrada no válida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if(score_str.matches("-?\\d+(\\.\\d+)?") && Long.parseLong(score_str) > 0){
                        int score = Integer.parseInt(score_str);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Ingrese un puntaje socioeconómico válido", 
                            "Error: Entrada no válida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } 
            }
        }
        
        //Actualizar componentes
        panel.interfaz.updateScreen();

    }
    
    boolean checkTextFieldEmpty(JTextField box){
        String text = box.getText().trim();
        return text.isEmpty() || text.startsWith("Ingrese");
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
