package main;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class UI{
    private MainPanel panel;
    Graphics2D grafica;
    Font defaultFont;
    
    public UI(MainPanel panel){
        this.panel = panel;
        defaultFont = new Font("Bahnschrift Light", Font.PLAIN, 16);
        
        drawTextFields();
        drawButtons();
    }
    
    public void draw(Graphics2D grafica){
        this.grafica = grafica;
        
        grafica.setFont(new Font("Bahnschrift Light", Font.PLAIN, 20));
        grafica.setColor(Color.white);
        
        if(panel.currentScreen == panel.mainScreen){
            drawMainScreen();
        } else if(panel.currentScreen == panel.menuScreen){
            drawMenuScreen();
        }
    }
    
    /*
    Nota. Cuando agregas componentes de Swing al panel con panel.add(), se quedan ahí hasta que los eliminas manualmente,
    así que es necesario actualizar los componentes cuando cambias de pantalla.
    */
    public void updateScreen(){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        
        drawTextFields();
        drawButtons();
    }
    
    public void drawButtons(){
        //Pantalla de inicio
        if(panel.currentScreen == panel.mainScreen){
            JButton login = new JButton("Ingresar");
            login.setBounds(getXforCenteredRect(panel.tile * 3), panel.tile * 19/2, panel.tile * 3, panel.tile * 3/4);
            login.setFont(defaultFont.deriveFont(Font.BOLD, 17F));
            login.setForeground(Color.white);
            login.setFocusPainted(false);
            login.setBorderPainted(false);
            login.setContentAreaFilled(true);
            login.setBackground(new Color(245, 52, 59)); //Rojo más claro
            login.addMouseListener(new CurrentScreenControler(panel, 1)); //Para avanzar a la siguiente pantalla
            panel.add(login);
        }
    }
    
    public void drawTextFields(){
        //Pantalla de inicio
        if(panel.currentScreen == panel.mainScreen){
            JTextField userBox = new JTextField(40);
            userBox.setBounds(panel.tile * 9 , panel.tile * 7, panel.tile * 6, panel.tile * 3/4);
            userBox.setFont(defaultFont);
            addPlaceholder(userBox, "Ingrese su usuario");
            panel.add(userBox);

            JTextField passwordBox = new JTextField(40);
            passwordBox.setBounds(panel.tile * 9 , panel.tile * 8, panel.tile * 6, panel.tile * 3/4);
            passwordBox.setFont(defaultFont);
            addPlaceholder(passwordBox, "Ingrese su contraseña");
            panel.add(passwordBox);
        } 

    }
    
    public void drawMenuScreen(){
        grafica.drawString("Test", 100, 400);
    }
    
    public void drawMainScreen(){
        grafica.setColor(Color.white);
        grafica.fillRect(0, 0, panel.screenWidth, panel.screenHeight);
        grafica.setFont(grafica.getFont().deriveFont(Font.BOLD, 70F)); //Cambiar el tamaño actual de la fuente
        String text = "SISTEMA DE RESIDENCIAS";
        int x = getXforCenteredText(text);
        int y = panel.tile * 5;
        
        grafica.setColor(Color.black);
        grafica.drawString(text, x, y);
        
        x = getXforCenteredRect(panel.tile * 8);
        y += panel.tile;
        
        grafica.setColor(new Color(179, 35, 41)); //Rojo
        grafica.fillRoundRect(x, y, panel.tile * 8, panel.tile * 5, 40, 40);
        
        grafica.setColor(new Color(158, 25, 30)); //Rojo
        grafica.fillRect(0, panel.tile, panel.tile * 24, panel.tile * 3/2);
        
        grafica.setColor(new Color(130, 130, 130)); //Gris
        grafica.fillRect(0, 0, panel.tile * 24, panel.tile );
        
        grafica.setColor(new Color(74, 74, 74)); //Gris
        grafica.fillRect(panel.tile * 2, 0, panel.tile * 7, panel.tile * 3);

    }
    
    
    //Métodos auxiliares 
    
     public static void addPlaceholder(JTextField box, String placeholder) {
        box.setText(placeholder);
        box.setForeground(Color.GRAY);

        box.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (box.getText().equals(placeholder)) {
                    box.setText("");
                    box.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (box.getText().isEmpty()) {
                    box.setText(placeholder);
                    box.setForeground(Color.GRAY);
                }
            }
        });
    }
     
    public int getXforCenteredRect(int width){
        return (panel.screenWidth - width) / 2;
    }
    
    public int getYforCenteredRect(int height){
        return (panel.screenHeight - height) / 2;
    }
    
    public int getXforCenteredText(String texto){ 
        int length = (int)grafica.getFontMetrics().getStringBounds(texto, grafica).getWidth();
        int x = panel.screenWidth/2 - length/2;
        return x;
    }

}
