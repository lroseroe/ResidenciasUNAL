package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import residenciasunalhash.Estudiante;


public class MouseController implements MouseListener{
    MainPanel panel;
    //booleanos para verificar que boton se ha presionado y saber que mostrar en UI
    public boolean searchBtnPressed = false, removeStudentBtnPressed = false, editScoreBtnPressed = false;
    public boolean editNumPlacesBtnPressed = false, checkAvailabiltyBtnPressed = false, listStudentsBtnPressed = false; 
    public boolean asignPlacesBtnPressed = false, addStudentBtnPressed = false; 
    
    public boolean studentFound; //Solo para cuando estas buscando un estudiante o agregandolo
    public boolean studentEliminated;
    public long ID;
    
    public MouseController(MainPanel panel){
        this.panel = panel;
        ID = 0L;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object btn = e.getSource();
        
        if(panel.currentScreen == panel.studentInfoScreen){
            if(btn == panel.interfaz.searchBtn){
                searchBtnPressed = true;
                String IDstr = panel.interfaz.searchBox.getText();
                boolean valid = verifyValidLong(IDstr, "Ingrese un ID válido");
                
                if(!valid){
                    return; //Return para que no se borre lo de las cajas de texto
                } 
                
                ID = Long.parseLong(IDstr);
                Estudiante estudiante = panel.control.buscarEstudiante(ID);
                studentFound = estudiante != null;
                studentEliminated = false;

                if(studentFound){ //Muestra su información en pantalla
                    panel.interfaz.nameFilledText = new JLabel(estudiante.getNombre());
                    panel.interfaz.IDFilledText = new JLabel(Long.toString(ID));
                    panel.interfaz.scoreFilledText = new JLabel(Integer.toString(estudiante.getPuntaje()));
                }
            } 
            if(btn == panel.interfaz.removeStudentBtn){
                int choice = JOptionPane.showConfirmDialog(panel, "¿Está seguro?", "Eliminar estudiante", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if(choice == JOptionPane.YES_OPTION){
                    studentEliminated = panel.control.eliminarEstudiante(ID);     
                } 
                
                //Si no, solo se cierra la ventanita
            } 
            if(btn == panel.interfaz.editScoreBtn){
                String newScoreStr = JOptionPane.showInputDialog(panel, "Ingrese el nuevo puntaje socioeconómico", 
                        "Editar puntaje", JOptionPane.INFORMATION_MESSAGE);
                
                boolean valid = verifyValidInteger(newScoreStr, "Ingrese un puntaje socioeconómico válido");
                if(valid){
                    int newScore = Integer.parseInt(newScoreStr);
                    panel.control.actualizarPuntaje(ID, newScore);
                    panel.interfaz.scoreFilledText.setText(Integer.toString(newScore));
                } 
 
            }
        } 
        
        if(panel.currentScreen == panel.residenceAvailabilityScreen){
            if(btn == panel.interfaz.checkAvailabiltyBtn){
                checkAvailabiltyBtnPressed = true;
            } else if(btn == panel.interfaz.editNumPlacesBtn){
                editNumPlacesBtnPressed = true;
                
                String newNumPlacesStr = JOptionPane.showInputDialog(panel, "Ingrese la nueva cantidad de cupos", 
                        "Editar total de cupos", JOptionPane.INFORMATION_MESSAGE);
                
                boolean valid = verifyValidInteger(newNumPlacesStr, "Ingrese un número de cupos válido");
                if(valid){
                    int newNumPlaces = Integer.parseInt(newNumPlacesStr);
                    panel.control.cambiarNumCupos(newNumPlaces);
                }
            } 
        } 
        
        if(panel.currentScreen == panel.asignationInfoScreen){
            if(btn == panel.interfaz.listStudentsBtn){
                listStudentsBtnPressed = true;
                asignPlacesBtnPressed = false;
                
                panel.control.ordenarEstudiantes();
            } else if(btn == panel.interfaz.asignPlacesBtn){
                asignPlacesBtnPressed = true;
                listStudentsBtnPressed = false; 
                
                panel.control.asignarCupos();
            }
        } 
        
        if(panel.currentScreen == panel.editAsignationScreen){
            if(btn == panel.interfaz.addStudentBtn){
                addStudentBtnPressed = true;
                
                if(checkTextFieldEmpty(panel.interfaz.studentNamesBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentLastNamesBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentIDBox) ||
                   checkTextFieldEmpty(panel.interfaz.studentScoreBox)){
                   JOptionPane.showMessageDialog(panel, "Asegúrese de llenar todos los campos", 
                   "Error: Panel vacío", JOptionPane.ERROR_MESSAGE);
                    return; //return para no actualizar componentes aún
                } 
                
                String names = panel.interfaz.studentNamesBox.getText();
                String lastNames = panel.interfaz.studentLastNamesBox.getText();
                String ID_str = panel.interfaz.studentIDBox.getText();
                String score_str = panel.interfaz.studentScoreBox.getText();
                
                boolean validID = verifyValidLong(ID_str, "Ingrese un ID válido");
                boolean validScore = verifyValidLong(ID_str, "Ingrese un puntaje socioeconómico válido");
                if(!validID || !validScore){
                    return;
                }
                
                long IDnumber = Long.parseLong(ID_str);
                int score = Integer.parseInt(score_str);

                studentFound = !panel.control.insertarEstudiante(IDnumber, names + " " + lastNames, score);
                if(studentFound){
                    JOptionPane.showMessageDialog(panel, "Este estudiante ya está registrado en el sistema.", 
                        "Error: Estudiante encontrado", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Estudiante registrado con éxito.", 
                        "Estudiante registrado", JOptionPane.INFORMATION_MESSAGE);
                }
                
            }
        }
        
        //Actualizar componentes
        panel.interfaz.updateScreen();

    }
    
    
    //Métodos auxiliares
    public boolean verifyValidLong(String str, String message){
        if((str == null || !str.matches("-?\\d+(\\.\\d+)?") || Long.parseLong(str) < 0)){ //Verificar string válido
            JOptionPane.showMessageDialog(panel, message, "Error. Entrada no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
    }
    
    public boolean verifyValidInteger(String str, String message){
        if((str == null || !str.matches("-?\\d+(\\.\\d+)?") || Integer.parseInt(str) < 0)){ //Verificar string válido
            JOptionPane.showMessageDialog(panel, message, "Error. Entrada no válida", JOptionPane.ERROR_MESSAGE);
            return false;
        } 
        return true;
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
