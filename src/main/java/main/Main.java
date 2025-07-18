package main;

import javax.swing.JFrame;

public class Main {
    public static JFrame ventana = new JFrame();
    
    public static void main(String[] args) {
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Sistema de Residencias");
        
        MainPanel panel = new MainPanel();
        ventana.add(panel);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
       
    }
}

