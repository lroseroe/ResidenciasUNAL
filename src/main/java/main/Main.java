package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import residenciasunalhash.CriptoCode;

public class Main {
    public static JFrame ventana = new JFrame();
    
    public static void main(String[] args) {
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Sistema de Residencias");
        new Main().setIcon();
        
        MainPanel panel = new MainPanel();
        ventana.add(panel);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        
    }
    
    public void setIcon(){
        BufferedImage icon;
        try {
            icon = ImageIO.read(getClass().getResourceAsStream("/university.png"));
            ventana.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

