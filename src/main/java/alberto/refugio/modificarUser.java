/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public void modificar(){
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "UPDATE users set uname ='"+nombre.getText()+"' , isRoot = '"+root.isSelected()+"' , uage = "+Integer.parseInt(edad.getText())+""
                    + "WHERE uemail = '"+email.getText()+"';";
            stmt.execute(query);
            stmt.close();
            conexion.close();
            App.setRoot("Secondary");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String encryptPassword(String password) {
        try {
            // Crear un objeto MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Aplicar el algoritmo de hash a la contraseña
            byte[] hash = digest.digest(password.getBytes());
            // Convertir el hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción si el algoritmo no está disponible
            e.printStackTrace();
            return null;
        }
    }
    
    
    public void reset(){
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "UPDATE users set upassw ='"+encryptPassword("1234")+"' WHERE uemail = '"+email.getText()+"';";
            stmt.execute(query);
            stmt.close();
            conexion.close();
            
            errMess.setText("CONTRASEÑA RESETADA");
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }
    
    public void volver(){
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

}
