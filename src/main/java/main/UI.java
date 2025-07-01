package main;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class UI{
    private MainPanel panel;
    Graphics2D grafica;
    Font defaultFont;
    
    BufferedImage mainScreenImage;
    BufferedImage defaultMenuScreenImage;
    BufferedImage menuScreenImage;
    
    public UI(MainPanel panel){
        this.panel = panel;
        
        //Cargar imagenes
        try{
            InputStream archivoFuente = getClass().getResourceAsStream("/Kanit-Regular.ttf");
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, archivoFuente);
            
            mainScreenImage = ImageIO.read(getClass().getResourceAsStream("/mainScreen.png"));
            defaultMenuScreenImage = ImageIO.read(getClass().getResourceAsStream("/defaultMenuScreen.png"));
            menuScreenImage = ImageIO.read(getClass().getResourceAsStream("/menuScreen.png"));
            
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
            grafica.drawImage(defaultMenuScreenImage, 0, 0, null);
        } else {
            grafica.drawImage(menuScreenImage, 0, 0, null);
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
        Border lineBorder = BorderFactory.createLineBorder(Color.white, 1);
        
        if(panel.currentScreen == panel.mainScreen){
            JButton login = new JButton("Ingresar");
            login.setBounds(panel.tile * 71/4, panel.tile * 9, panel.tile * 3, panel.tile * 3/4);
            login.setFont(defaultFont.deriveFont(Font.BOLD, 20F));
            login.setForeground(Color.white);
            login.setFocusPainted(false);
            login.setContentAreaFilled(true);
            login.setBorder(lineBorder);
            login.setBackground(new Color(153, 43, 43)); //Rojo
            login.addMouseListener(new CurrentScreenControler(panel, panel.menuScreen)); //Para avanzar a la siguiente pantalla
            panel.add(login);
        } else{
            JButton option1 = new JButton("Información estudiantil");
            option1.setBounds(0, panel.tile * 2, panel.tile * 7, panel.tile);
            option1.setFont(defaultFont.deriveFont(Font.PLAIN, 20F));
            option1.setForeground(Color.white);
            option1.setFocusPainted(false);
            option1.setBorder(lineBorder);
            option1.setContentAreaFilled(true);
            option1.setBackground(new Color(153, 43, 43)); //Rojo
            option1.addMouseListener(new CurrentScreenControler(panel, panel.studentInfoScreen));
            panel.add(option1);
            
            JButton option2 = new JButton("Disponibilidad de cupos");
            option2.setBounds(0, panel.tile * 3, panel.tile * 7, panel.tile);
            option2.setFont(defaultFont.deriveFont(Font.PLAIN, 20F));
            option2.setForeground(Color.white);
            option2.setFocusPainted(false);
            option2.setBorder(lineBorder);
            option2.setContentAreaFilled(true);
            option2.setBackground(new Color(153, 43, 43)); //Rojo
            option2.addMouseListener(new CurrentScreenControler(panel, panel.residenceAvailabilityScreen)); //Cambiar a esta pantalla
            panel.add(option2);
            
            JButton option3 = new JButton("Consultar asignación");
            option3.setBounds(0, panel.tile * 4, panel.tile * 7, panel.tile);
            option3.setFont(defaultFont.deriveFont(Font.PLAIN, 20F));
            option3.setForeground(Color.white);
            option3.setFocusPainted(false);
            option3.setBorder(lineBorder);
            option3.setContentAreaFilled(true);
            option3.setBackground(new Color(153, 43, 43)); //Rojo
            option3.addMouseListener(new CurrentScreenControler(panel, panel.asignationInfoScreen)); 
            panel.add(option3);
            
            JButton option4 = new JButton("Editar asignación");
            option4.setBounds(0, panel.tile * 5, panel.tile * 7, panel.tile);
            option4.setFont(defaultFont.deriveFont(Font.PLAIN, 20F));
            option4.setForeground(Color.white);
            option4.setFocusPainted(false);
            option4.setBorder(lineBorder);
            option4.setContentAreaFilled(true);
            option4.setBackground(new Color(153, 43, 43)); //Rojo
            option4.addMouseListener(new CurrentScreenControler(panel, panel.editAsignationScreen)); 
            panel.add(option4);
            
            JButton option5 = new JButton("Información adicional");
            option5.setBounds(0, panel.tile * 11, panel.tile * 7, panel.tile);
            option5.setFont(defaultFont.deriveFont(Font.PLAIN, 20F));
            option5.setForeground(Color.white);
            option5.setFocusPainted(false);
            option5.setBorder(lineBorder);
            option5.setContentAreaFilled(true);
            option5.setBackground(new Color(153, 43, 43)); //Rojo
            option5.addMouseListener(new CurrentScreenControler(panel, panel.systemInfoScreen)); 
            panel.add(option5);
            
            //Otras opciones
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
