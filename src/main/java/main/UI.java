package main;

import java.awt.*;

public class UI {
    private MainPanel panel;
    Graphics2D grafica;
    
    public UI(MainPanel panel){
        this.panel = panel;
    }
    
    public void draw(Graphics2D grafica){
        this.grafica = grafica;
        
        grafica.setColor(Color.WHITE);
        grafica.setFont(new Font("Arial", Font.BOLD, 20));
        grafica.drawString("Holaaa", 400, 200);
    }
    
    //Crear métodos especificos que se llamen en draw() para tener todo más organizado
}
