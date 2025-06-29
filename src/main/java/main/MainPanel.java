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
    final int totalScreens = 5;
    
    //Aqui se debería inicializar todo lo del programa principal 
    
    UI interfaz = new UI(this);
            
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
        System.out.println("screen: " + currentScreen);
    }
    
    public void changeScreen(int direc){
        int newScreen = currentScreen + direc;
        if(newScreen >= 0 && newScreen < totalScreens){
            currentScreen = newScreen;
        }
        
        interfaz.updateScreen();
    }
}
