/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

/**
 *
 * @author neong
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class modificarAdoptante {

    public static Persona p;

    @FXML
    private Button Modificar;

    @FXML
    private Button Volver;

    @FXML
    private TextField ema;

    @FXML
    private Label errMess;

    @FXML
    private TextField nombre;

    @FXML
    private TextField id;

    @FXML
    private TextField anidop;

    @FXML
    private TextField tel;

    public void initialize() {
        errMess.setText("");
        nombre.setText(p.getNombre());
        tel.setText(p.getNumber());
        ema.setText(p.getEmail());
        id.setText(p.getID());
        anidop.setText(p.getNum_animales_adoptados() + "");

    }

    public void modificar() {

        if (!nombre.getText().equalsIgnoreCase("") && !nombre.getText().equalsIgnoreCase(" ")
                && !ema.getText().equalsIgnoreCase("") && !ema.getText().equalsIgnoreCase(" ")
                && !tel.getText().equalsIgnoreCase("") && !tel.getText().equalsIgnoreCase(" ")) {
            
            
            try {
                int min = Integer.parseInt(tel.getText());
                 if (min >= 10000000 && tel.getText().length() <= 10) {
                     // Carga el controlador JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establece la conexión con la base de datos
                String url = "jdbc:mysql://localhost:3307/shelterly";
                String usuario = "root";
                String contraseña = "";
                Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

                Statement stmt = conexion.createStatement();

                String query = "UPDATE persona set nombre ='" + nombre.getText() + "' , email = '" + ema.getText() + "', phone_number=" + Integer.parseInt(tel.getText()) + "  WHERE identification = '" + p.getID() + "';";
                stmt.executeUpdate(query);
                stmt.close();
                conexion.close();
                App.setRoot("Secondary");
                 }else{
                     errMess.setText("Telefono debe ser mayor a 8 digitos y menor a 10 digitos");
                 } 
                
                
                
                
                
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
                // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
            } catch (IOException ex) {
                Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NumberFormatException e){
                errMess.setText("Telefono debe ser numerico");
            }
        }else{
            errMess.setText("Ningun campo debe estar vacio");
        }

    }

    public void volver() {
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(modificarAdoptante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
