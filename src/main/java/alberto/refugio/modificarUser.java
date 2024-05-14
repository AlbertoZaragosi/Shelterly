/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author neong
 */
public class modificarUser {

    @FXML
    private Label errMess;

    @FXML
    private Button Modificar;

    @FXML
    private Button RESETBUTTON;
    
    @FXML
    private Button Volver;

    @FXML
    private TextField edad;

    @FXML
    private TextField email;

    @FXML
    private TextField nombre;

    @FXML
    private CheckBox root;

    public static User u;

    @FXML
    public void initialize() {
        nombre.setText(u.getName());
        edad.setText(""+u.getAge());
        if (u.isRoot) {
            root.setSelected(true);
        } else {
            root.setSelected(false);
        }
        email.setText(u.getEmail());
    }
    
    public void volver(){
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

}
