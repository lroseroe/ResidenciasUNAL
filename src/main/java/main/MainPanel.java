package main;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel{
    final int originalTile = 16;
    final int scale = 3;
    final int tile = originalTile * scale; 
    
    final int screenCol = 24;
    final int screenRow = 14;
    
    //Proporción 16:9 (Casi) 
    final int screenWidth = screenCol * tile; //1152 px
    final int screenHeight = screenRow * tile; //672
    
    //Distintas pantallas del sistema
    int currentScreen;
    final int mainScreen = 0;
    final int menuScreen = 1;
    final int studentInfoScreen = 2;
    final int residenceAvailabilityScreen = 3;
    final int asignationInfoScreen = 4;
    final int editAsignationScreen = 5;
    final int systemInfoScreen = 6;
    final int totalScreens = 7;
    
    
    public int totalResidences; //Cupos totales
    public int takenResidences; //Está variable debe cambiar cada que se asigne un cupo  
    public int availableResidences;  //Actualizar esto también
    
    //Aqui se debería inicializar todo lo del programa principal 
    
    
    MouseController mouseCtrl = new MouseController(this);
    UI interfaz = new UI(this);
    Config config = new Config(this);
    SystemControl control = new SystemControl(10000000, this); //Máximo 10 millones de cupos
            
    public MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setLayout(null);
        
        currentScreen = mainScreen;
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D) g;
        interfaz.draw(g2); 
    }
    
    public void changeScreen(int screen){
        if(screen >= 0 && screen < totalScreens){
            currentScreen = screen;
        }
        
        mouseCtrl.searchBtnPressed = false;
        mouseCtrl.checkAvailabiltyBtnPressed = false;
        mouseCtrl.editNumPlacesBtnPressed = false;
        mouseCtrl.listStudentsBtnPressed = false;
        mouseCtrl.asignPlacesBtnPressed = false;
        mouseCtrl.addStudentBtnPressed = false;
        
        
        interfaz.updateScreen();
    }
}
