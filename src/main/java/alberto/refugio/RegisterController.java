package alberto.refugio;

import java.io.IOException;
import javafx.fxml.FXML;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

public class RegisterController {

    @FXML
    private Button Register;

    @FXML
    private Pane panel;

    @FXML
    private Pane panel2;

    @FXML
    private TextField uEmail;

    @FXML
    private PasswordField uPassw;

    @FXML
    private TextField uname;
    
    @FXML
    private Label errMess;

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

    public void Register() {

        if (!uEmail.getText().equalsIgnoreCase("") && !uEmail.getText().equalsIgnoreCase(" ")
                && !uname.getText().equalsIgnoreCase("") && !uname.getText().equalsIgnoreCase(" ")
                && !uPassw.getText().equalsIgnoreCase("") && !uPassw.getText().equalsIgnoreCase(" ")) {
            try {
                // Carga el controlador JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establece la conexión con la base de datos
                String url = "jdbc:mysql://localhost:3307/shelterly";
                String usuario = "root";
                String contraseña = "";
                Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

                Statement stmt = conexion.createStatement();

                User u = new User(uname.getText(), uEmail.getText(), encryptPassword(uPassw.getText()));
                String query = "insert into users (uname,uemail,upassw) values('" + u.getName() + "','" + u.getEmail() + "','" + u.getPassword() + "');";

                stmt.executeUpdate(query);

                stmt.close();
                conexion.close();
                App.setRoot("primary");

            } catch (ClassNotFoundException | SQLIntegrityConstraintViolationException ex) {
                ex.printStackTrace();
                // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
            } catch (SQLException e) {

            } catch (IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            errMess.setText("Ningun campo debe estar vacio");
        }

    }

    public void initialize() {
        panel2.toFront();
        errMess.setText("");
    }

    public void volver() {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
