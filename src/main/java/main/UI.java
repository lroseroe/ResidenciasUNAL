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
    BufferedImage profileImage;
    
    //----------- Botones para cada acción ---------
    //studentInfoScreen
    JButton searchBtn; //Buscar un estudiante con ID
    JButton removeStudentBtn; //Remover estudiante del sistema
    JButton editScoreBtn; //Editar su puntaje socioeconomico
    
    //residenceAvailabilityScreen
    JButton editNumPlacesBtn; //Editar cantidad de cupos total
    JButton checkAvailabiltyBtn; //Revisar cantidad de cupos disponibles
    JButton confirmEditingBtn; 
    
    //asignationInfoScreen
    JButton listStudentsBtn; //Listar estudiantes con y sin cupo obtenido
    
    //editAsignationScreen
    JButton addStudentBtn; //Registrar estudiante al sistema
    
    //Cajas de texto para recibir información del usuario
    JTextField searchBox; //Recibir el ID del estudiante para buscarlo

    //Datos del estudiante al agregarlo
    JTextField studentNamesBox; 
    JTextField studentLastNamesBox;
    JTextField studentIDBox;
    JTextField studentScoreBox;
    
    public UI(MainPanel panel){
        this.panel = panel;
        
        //Cargar imagenes
        try{
            InputStream archivoFuente = getClass().getResourceAsStream("/Kanit-Regular.ttf");
            defaultFont = Font.createFont(Font.TRUETYPE_FONT, archivoFuente);
            
            mainScreenImage = ImageIO.read(getClass().getResourceAsStream("/mainScreen.png"));
            defaultMenuScreenImage = ImageIO.read(getClass().getResourceAsStream("/defaultMenuScreen.png"));
            menuScreenImage = ImageIO.read(getClass().getResourceAsStream("/menuScreen.png"));
            profileImage = ImageIO.read(getClass().getResourceAsStream("/profile.png"));
            
        } catch(FontFormatException | IOException e){
            e.printStackTrace();
        }
        
        drawTextFields();
        drawButtons();
        drawLabels();
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
            if(panel.mouseCtrl.searchBtnPressed && panel.mouseCtrl.studentFound){
                grafica.drawImage(profileImage, panel.tile * 8, panel.tile * 5, null);
            }
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
        drawLabels();
    }
    
    public void drawLabels(){
        if(panel.mouseCtrl.searchBtnPressed){
            if(!panel.mouseCtrl.studentFound){
                JLabel notFoundText = new JLabel("Estudiante no encontrado.");
                notFoundText.setBounds(panel.tile * 8, panel.tile * 5, panel.tile * 10, panel.tile * 3/4);
                notFoundText.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                notFoundText.setForeground(Color.black);
                notFoundText.setBackground(Color.white);
                panel.add(notFoundText);
            } else {
                JLabel nameText = new JLabel("Nombre: ");
                nameText.setBounds(panel.tile * 14, panel.tile * 5, panel.tile * 10, panel.tile * 3/4);
                nameText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                nameText.setForeground(Color.black);
                nameText.setBackground(Color.white);
                panel.add(nameText);
                
                JLabel nameFilledText = new JLabel("###### ####### ####### #########");
                nameFilledText.setBounds(panel.tile * 14, panel.tile * 11/2, panel.tile * 10, panel.tile * 3/4);
                nameFilledText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                nameFilledText.setForeground(Color.gray);
                nameFilledText.setBackground(Color.white);
                panel.add(nameFilledText);
                
                JLabel IDText = new JLabel("ID: ");
                IDText.setBounds(panel.tile * 14, panel.tile * 6, panel.tile * 10, panel.tile * 3/4);
                IDText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                IDText.setForeground(Color.black);
                IDText.setBackground(Color.white);
                panel.add(IDText);
                
                JLabel IDFilledText = new JLabel("############");
                IDFilledText.setBounds(panel.tile * 14, panel.tile * 13/2, panel.tile * 10, panel.tile * 3/4);
                IDFilledText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                IDFilledText.setForeground(Color.gray);
                IDFilledText.setBackground(Color.white);
                panel.add(IDFilledText);
                
                JLabel ScoreText = new JLabel("Puntaje socioeconómico: ");
                ScoreText.setBounds(panel.tile * 14, panel.tile * 7, panel.tile * 10, panel.tile * 3/4);
                ScoreText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                ScoreText.setForeground(Color.black);
                ScoreText.setBackground(Color.white);
                panel.add(ScoreText);
                
                JLabel ScoreFilledText = new JLabel("####");
                ScoreFilledText.setBounds(panel.tile * 14, panel.tile * 15/2, panel.tile * 10, panel.tile * 3/4);
                ScoreFilledText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
                ScoreFilledText.setForeground(Color.gray);
                ScoreFilledText.setBackground(Color.white);
                panel.add(ScoreFilledText);
            }
        } else if(panel.mouseCtrl.checkAvailabiltyBtnPressed){
            JLabel availabilityText = new JLabel("Hay " + panel.availableResidences + " cupos disponibles.");
            availabilityText.setBounds(panel.tile * 8, panel.tile * 4, panel.tile * 10, panel.tile * 3/4);
            availabilityText.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            availabilityText.setForeground(Color.black);
            availabilityText.setBackground(Color.white);
            panel.add(availabilityText);
        } else if(panel.currentScreen == panel.systemInfoScreen){
            JLabel creditsText = new JLabel("Programa creado por: ");
            creditsText.setBounds(panel.tile * 8, panel.tile * 3, panel.tile * 10, panel.tile * 3/4);
            creditsText.setFont(defaultFont.deriveFont(Font.BOLD, 16F));
            creditsText.setForeground(Color.black);
            creditsText.setBackground(Color.white);
            panel.add(creditsText);
            
            JLabel creditsText1 = new JLabel("Juan Thomas Contreras Pinzón");
            creditsText1.setBounds(panel.tile * 8, panel.tile * 4, panel.tile * 10, panel.tile * 3/4);
            creditsText1.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            creditsText1.setForeground(Color.black);
            creditsText1.setBackground(Color.white);
            panel.add(creditsText1);
            
            JLabel creditsText2 = new JLabel("Sara Valentina Fajardo Lombana");
            creditsText2.setBounds(panel.tile * 8, panel.tile * 5, panel.tile * 10, panel.tile * 3/4);
            creditsText2.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            creditsText2.setForeground(Color.black);
            creditsText2.setBackground(Color.white);
            panel.add(creditsText2);
            
            JLabel creditsText3 = new JLabel("Luisa María Rosero Espinosa");
            creditsText3.setBounds(panel.tile * 8, panel.tile * 6, panel.tile * 10, panel.tile * 3/4);
            creditsText3.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            creditsText3.setForeground(Color.black);
            creditsText3.setBackground(Color.white);
            panel.add(creditsText3);
            
            JLabel creditsText4 = new JLabel("* Imagenes tomadas de Freepik");
            creditsText4.setBounds(panel.tile * 8, panel.tile * 10, panel.tile * 10, panel.tile * 3/4);
            creditsText4.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            creditsText4.setForeground(Color.black);
            creditsText4.setBackground(Color.white);
            panel.add(creditsText4);
        }
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
            
            JButton option4 = new JButton("Agregar asignación");
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
            if(panel.currentScreen == panel.studentInfoScreen){
                searchBtn = new JButton("Buscar estudiante");
                searchBtn.setBounds(panel.tile * 8, panel.tile * 4, panel.tile * 4, panel.tile * 3/4);
                searchBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                searchBtn.setForeground(Color.white);
                searchBtn.setFocusPainted(false);
                searchBtn.setBorder(lineBorder);
                searchBtn.setContentAreaFilled(true);
                searchBtn.setBackground(new Color(153, 43, 43)); //Rojo
                searchBtn.addMouseListener(panel.mouseCtrl); 
                panel.add(searchBtn);
                
                if(panel.mouseCtrl.searchBtnPressed && panel.mouseCtrl.studentFound){
                    removeStudentBtn = new JButton("Eliminar estudiante");
                    removeStudentBtn.setBounds(panel.tile * 14, panel.tile * 10, panel.tile * 4, panel.tile * 3/4);
                    removeStudentBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                    removeStudentBtn.setForeground(Color.white);
                    removeStudentBtn.setFocusPainted(false);
                    removeStudentBtn.setBorder(lineBorder);
                    removeStudentBtn.setContentAreaFilled(true);
                    removeStudentBtn.setBackground(new Color(153, 43, 43)); //Rojo
                    removeStudentBtn.addMouseListener(panel.mouseCtrl); 
                    panel.add(removeStudentBtn);
                    
                    editScoreBtn = new JButton("Editar puntaje");
                    editScoreBtn.setBounds(panel.tile * 37/2, panel.tile * 10, panel.tile * 4, panel.tile * 3/4);
                    editScoreBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                    editScoreBtn.setForeground(Color.white);
                    editScoreBtn.setFocusPainted(false);
                    editScoreBtn.setBorder(lineBorder);
                    editScoreBtn.setContentAreaFilled(true);
                    editScoreBtn.setBackground(new Color(153, 43, 43)); //Rojo
                    editScoreBtn.addMouseListener(panel.mouseCtrl); 
                    panel.add(editScoreBtn);
                } 
            } else if(panel.currentScreen == panel.residenceAvailabilityScreen){
                checkAvailabiltyBtn = new JButton("Ver cupos disponibles");
                checkAvailabiltyBtn.setBounds(panel.tile * 8, panel.tile * 3, panel.tile * 5, panel.tile * 3/4);
                checkAvailabiltyBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                checkAvailabiltyBtn.setForeground(Color.white);
                checkAvailabiltyBtn.setFocusPainted(false);
                checkAvailabiltyBtn.setBorder(lineBorder);
                checkAvailabiltyBtn.setContentAreaFilled(true);
                checkAvailabiltyBtn.setBackground(new Color(153, 43, 43)); //Rojo
                checkAvailabiltyBtn.addMouseListener(panel.mouseCtrl); 
                panel.add(checkAvailabiltyBtn);
                
                editNumPlacesBtn = new JButton("Editar total de cupos");
                editNumPlacesBtn.setBounds(panel.tile * 8, panel.tile * 5, panel.tile * 5, panel.tile * 3/4);
                editNumPlacesBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                editNumPlacesBtn.setForeground(Color.white);
                editNumPlacesBtn.setFocusPainted(false);
                editNumPlacesBtn.setBorder(lineBorder);
                editNumPlacesBtn.setContentAreaFilled(true);
                editNumPlacesBtn.setBackground(new Color(153, 43, 43)); //Rojo
                editNumPlacesBtn.addMouseListener(panel.mouseCtrl); 
                panel.add(editNumPlacesBtn);
            } else if(panel.currentScreen == panel.asignationInfoScreen){
                listStudentsBtn = new JButton("Mostrar asignaciones");
                listStudentsBtn.setBounds(panel.tile * 8, panel.tile * 3, panel.tile * 5, panel.tile * 3/4);
                listStudentsBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                listStudentsBtn.setForeground(Color.white);
                listStudentsBtn.setFocusPainted(false);
                listStudentsBtn.setBorder(lineBorder);
                listStudentsBtn.setContentAreaFilled(true);
                listStudentsBtn.setBackground(new Color(153, 43, 43)); //Rojo
                listStudentsBtn.addMouseListener(panel.mouseCtrl); 
                panel.add(listStudentsBtn);
            } else if(panel.currentScreen == panel.editAsignationScreen){
                addStudentBtn = new JButton("Agregar estudiante");
                addStudentBtn.setBounds(panel.tile * 8, panel.tile * 7, panel.tile * 4, panel.tile * 3/4);
                addStudentBtn.setFont(defaultFont.deriveFont(Font.PLAIN, 18F));
                addStudentBtn.setForeground(Color.white);
                addStudentBtn.setFocusPainted(false);
                addStudentBtn.setBorder(lineBorder);
                addStudentBtn.setContentAreaFilled(true);
                addStudentBtn.setBackground(new Color(153, 43, 43)); //Rojo
                addStudentBtn.addMouseListener(panel.mouseCtrl); 
                panel.add(addStudentBtn);
            }
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
        } else if(panel.currentScreen == panel.studentInfoScreen){
            searchBox = new JTextField(40);
            searchBox.setBounds(panel.tile * 8 , panel.tile * 3, panel.tile * 47/8, panel.tile * 3/4);
            searchBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(searchBox, "  Ingrese el ID del estudiante");
            panel.add(searchBox);
        } else if(panel.currentScreen == panel.editAsignationScreen){
            studentNamesBox = new JTextField(40);
            studentNamesBox.setBounds(panel.tile * 8 , panel.tile * 3, panel.tile * 8, panel.tile * 3/4);
            studentNamesBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(studentNamesBox, "  Ingrese el/los nombre(s) del estudiante");
            panel.add(studentNamesBox);
            
            studentLastNamesBox = new JTextField(40);
            studentLastNamesBox.setBounds(panel.tile * 8 , panel.tile * 4, panel.tile * 8, panel.tile * 3/4);
            studentLastNamesBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(studentLastNamesBox, "  Ingrese el/los apellido(s) del estudiante");
            panel.add(studentLastNamesBox);
            
            studentIDBox = new JTextField(40);
            studentIDBox.setBounds(panel.tile * 8 , panel.tile * 5, panel.tile * 8, panel.tile * 3/4);
            studentIDBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(studentIDBox, "  Ingrese el ID del estudiante");
            panel.add(studentIDBox);
            
            studentScoreBox = new JTextField(40);
            studentScoreBox.setBounds(panel.tile * 8 , panel.tile * 6, panel.tile * 8, panel.tile * 3/4);
            studentScoreBox.setFont(defaultFont.deriveFont(Font.PLAIN, 16F));
            addPlaceholder(studentScoreBox, "  Ingrese el puntaje socioeconómico del estudiante");
            panel.add(studentScoreBox);
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
