package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CurrentScreenControler extends MouseAdapter {
    //Clase para cambiar de una pantalla a otra
    MainPanel panel;
    int screen;
    
    public CurrentScreenControler(MainPanel panel, int screen){
        this.panel = panel;
        this.screen = screen;
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        panel.changeScreen(screen);
    }

    

}
