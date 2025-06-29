package main;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UI{
    private MainPanel panel;
    Graphics2D grafica;
    Font defaultFont;
    
    BufferedImage mainScreenImage;
    
    public UI(MainPanel panel){
        this.panel = panel;
        
        //Cargar imagenes
        try{
            InputStream archivoFuente = getClass().getResourceAsStream("/Kanit-Regular.ttf");
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, archivoFuente);
            
            mainScreenImage = ImageIO.read(getClass().getResourceAsStream("/mainScreen.png"));
        } catch(FontFormatException | IOException e){
            e.printStackTrace();
        }
        
        drawTextFields();
        drawButtons();
    }
    
    public void draw(Graphics2D grafica){
        this.grafica = grafica;
        
        grafica.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
        grafica.setColor(Color.white);
        
        if(panel.currentScreen == panel.mainScreen){
            grafica.drawImage(mainScreenImage, 0, 0, null);
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
            login.setBounds(panel.tile * 71/4, panel.tile * 9, panel.tile * 3, panel.tile * 3/4);
            login.setFont(defaultFont.deriveFont(Font.BOLD, 20F));
            login.setForeground(Color.white);
            login.setFocusPainted(false);
            login.setBorderPainted(false);
            login.setContentAreaFilled(true);
            login.setBackground(new Color(153, 43, 43)); //Rojo
            login.addMouseListener(new CurrentScreenControler(panel, 1)); //Para avanzar a la siguiente pantalla
            panel.add(login);
        }
    }
    
    public void drawTextFields(){
        //Pantalla de inicio
        if(panel.currentScreen == panel.mainScreen){
            JTextField userBox = new JTextField(40);
            userBox.setBounds(panel.tile * 65/4 , panel.tile * 5, panel.tile * 47/8, panel.tile * 3/4);
            userBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(userBox, "  Ingrese su usuario");
            panel.add(userBox);

            JTextField passwordBox = new JTextField(40);
            passwordBox.setBounds(panel.tile * 65/4 , panel.tile * 13/2, panel.tile * 47/8, panel.tile * 3/4);
            passwordBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(passwordBox, "  Ingrese su contraseña");
            panel.add(passwordBox);
        } 

    }
    
    public void drawMenuScreen(){
        grafica.drawString("Test", 100, 400);
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
