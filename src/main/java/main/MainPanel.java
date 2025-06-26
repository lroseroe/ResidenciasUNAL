package main;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel{
    final int originalTile = 16;
    final int scale = 3;
    final int tile = originalTile * scale; 
    
    final int screenCol = 24;
    final int screenRow = 14;
    
    final int screenWidth = screenCol * tile;
    final int screenHeight = screenRow * tile;
    
    //Aqui se debería inicializar todo lo del programa principal 
    
    UI interfaz = new UI(this);
            
    public MainPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D) g;
        interfaz.draw(g2); 
    }
}
