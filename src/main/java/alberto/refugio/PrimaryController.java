package alberto.refugio;

import java.io.IOException;
import javafx.fxml.FXML;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Label messageError;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Label registerText;

    @FXML
    private Button showPassw;

    @FXML
    private TextField userMail;

    @FXML
    private PasswordField userPassw;

    public static void connect() {
        // Establece la conexión a la base de datos
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery("Select uname from users");

            while (rs.next()) {
                String name = rs.getString("uname");
                System.out.println(name);
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
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

    public void initialize() {
        messageError.setText("");
        PasswordLabel.setVisible(false);
    }

    public void Login() {
        if (!userMail.getText().equalsIgnoreCase("") && !userMail.getText().equalsIgnoreCase(" ") && 
                !userPassw.getText().equalsIgnoreCase("") && !userPassw.getText().equalsIgnoreCase(" ")) {
            try {
                // Using jdbc
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Conection to db
                String url = "jdbc:mysql://localhost:3307/shelterly";
                String usuario = "root";
                String contraseña = "";
                Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

                Statement stmt = conexion.createStatement();

                //Create the user
                User u1 = new User(this.userMail.getText(), encryptPassword(this.userPassw.getText()));

                //Query
                ResultSet rs = stmt.executeQuery("Select * from users where uemail='" + u1.email + "';");

                if (rs.next()) {
                    String name = rs.getString("uname");
                    String email = rs.getString("uemail");
                    boolean isRoot = rs.getBoolean("isRoot");
                    //int age = rs.getInt("uage");
                    String passw = rs.getString("upassw");
                    if (encryptPassword(this.userPassw.getText()).equals(passw)) {
                        u1 = new User(name, email, isRoot, passw);
                        SecondaryController.u = u1;
                        App.setRoot("secondary");

                    } else {
                        messageError.setText("Incorrect password");
                    }

                } else {
                    messageError.setText("That user doesn't exists");
                }

                rs.close();
                stmt.close();
                conexion.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
            } catch (IOException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            messageError.setText("Fields can't be empty");
        }
            

    }

    public void openCreateAccountWindow() {
        try {

            App.setRoot("register");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void togleView() {
        if (PasswordLabel.isVisible()) {
            PasswordLabel.setVisible(false);
        } else {
            PasswordLabel.setVisible(true);
        }
    }

    public void fillLabel() {
        PasswordLabel.setText(userPassw.getText());
    }

}
