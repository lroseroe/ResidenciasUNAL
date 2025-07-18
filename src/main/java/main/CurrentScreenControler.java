package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

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
        if(!panel.mouseCtrl.checkTextFieldEmpty(panel.interfaz.userBox) && panel.interfaz.userBox.getText().equals("admin")
           && !panel.mouseCtrl.checkTextFieldEmpty(panel.interfaz.userBox) && panel.interfaz.passwordBox.getText().equals("123")){
            panel.changeScreen(screen);
        } else {
            JOptionPane.showMessageDialog(panel, "Ingrese correctamente su usuario y contraseña", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
        }

    }

    

}
