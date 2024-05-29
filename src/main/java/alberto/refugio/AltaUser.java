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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author neong
 */
public class AltaUser {

    @FXML
    private Label errMess;

    @FXML
    private Button Volver;

    @FXML
    private Button altaButton;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox root;

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
    
    public void initialize(){
        errMess.setText("");
    }

    public void darAlta() {
        if (!mail.getText().equalsIgnoreCase(" ") && !mail.getText().equalsIgnoreCase("")
                && !name.getText().equalsIgnoreCase(" ") && !name.getText().equalsIgnoreCase("")) {
            this.altaUser();
            try {
                App.setRoot("Secondary");
            } catch (IOException ex) {
                Logger.getLogger(AltaUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            errMess.setText("Ningun campo debe estar vacio");
        }

    }

    public void altaUser() {
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            boolean isRoot = root.isSelected();

            String query = "insert into users (uname,uemail,isRoot,upassw) values('" + name.getText() + "','" + mail.getText() + "'," + isRoot + ",'" + encryptPassword(password.getText()) + "');";

            stmt.executeUpdate(query);

            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void volver() {
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
