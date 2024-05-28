/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author neong
 */
public class darBajaAnimalController {

    public static animal animalito;

    @FXML
    private Button ADOPTAR;

    @FXML
    private TextField DNIPersona;

    @FXML
    private TextField TelefonoPersona;

    @FXML
    private Pane adopcion;

    @FXML
    private Button adopcionButton;

    @FXML
    private Button deathButton;

    @FXML
    private Label edadAnimalAdoptar;

    @FXML
    private Label edadAnimalFallecimiento;

    @FXML
    private TextField emailPersona;

    @FXML
    private Pane fallecimiento;

    @FXML
    private Label idAnimalAdoptar;

    @FXML
    private Label idAnimalFallecimiento;

    @FXML
    private Label nombreAnimalAdoptar;

    @FXML
    private Label nombreAnimalFallecimiento;

    @FXML
    private TextField nombrePersona;

    @FXML
    private Label pesoAnimalAdoptar;

    @FXML
    private Label pesoAnimalFallecimiento;

    @FXML
    private Label razaAnimalAdoptar;

    @FXML
    private Label razaAnimalFallecimiento;

    @FXML
    private Label tipoAnimalAdoptar;

    @FXML
    private Label tipoAnimalFallecimiento;

    @FXML
    private Label fechaEntradaFallecimiento;
    
    @FXML
    private Label fechaFallecimiento;

    @FXML
    private Label errMess;

    String fechaActual = "";

    public void initialize() {
        errMess.setText("");
        edadAnimalAdoptar.setText(animalito.getAge() + "");
        idAnimalAdoptar.setText(animalito.getId() + "");
        nombreAnimalAdoptar.setText(animalito.getName());
        tipoAnimalAdoptar.setText(animalito.getType());
        razaAnimalAdoptar.setText(animalito.getRace());
        pesoAnimalAdoptar.setText(animalito.getWeight() + "");

        edadAnimalFallecimiento.setText(animalito.getAge() + "");
        idAnimalFallecimiento.setText(animalito.getId() + "");
        nombreAnimalFallecimiento.setText(animalito.getName());
        tipoAnimalFallecimiento.setText(animalito.getType());
        razaAnimalFallecimiento.setText(animalito.getRace());
        pesoAnimalFallecimiento.setText(animalito.getWeight() + "");
        fechaEntradaFallecimiento.setText(animalito.getEntryingDate() + "");

        Date todayDate = Date.valueOf(LocalDate.now());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        fechaActual = sdf.format(todayDate);

        fechaFallecimiento.setText(fechaActual);
        
        adopcionButton.setDisable(true);
        adopcion.toFront();
        

    }

    public void frontAdopt() {
        adopcion.toFront();
        adopcionButton.setDisable(true);
        deathButton.setDisable(false);
    }

    public void frontDeath() {
        fallecimiento.toFront();
        adopcionButton.setDisable(false);
        deathButton.setDisable(true);
    }

    public void fallecerButton() {

        int num_adopted = this.checkDeath();
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "UPDATE animal SET exit_date = '" + fechaActual + "' WHERE aid=" + animalito.getId() + ";";

            stmt.executeUpdate(query);

            query = "UPDATE animal SET owner_id = 'Death' WHERE aid=" + animalito.getId() + ";";

            stmt.executeUpdate(query);

            query = "UPDATE persona SET animals_adopted = " + (num_adopted + 1) + " WHERE identification='Death';";

            stmt.executeUpdate(query);

            stmt.close();
            conexion.close();
            App.setRoot("secondary");
        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(darBajaAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkPerson() {
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "Select * from persona where identification = '" + DNIPersona.getText() + "';";
            ResultSet rs = stmt.executeQuery(query);
            boolean existe = rs.next();

            if (!existe) {
                String id = DNIPersona.getText();
                String name = nombrePersona.getText();
                String phone = TelefonoPersona.getText();
                String email = emailPersona.getText();
                String query2 = "insert into persona values ('" + name + "','" + email + "','" + phone + "','" + id + "',0);";

                stmt.executeUpdate(query2);
                rs.close();

                stmt.close();
                conexion.close();

                return 0;

            } else {
                int num_adopt = rs.getInt("animals_adopted");
                rs.close();
                stmt.close();
                conexion.close();
                return num_adopt;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int checkDeath() {
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "Select * from persona where identification = 'Death';";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int num_adopt = rs.getInt("animals_adopted");

            rs.close();
            stmt.close();
            conexion.close();
            return num_adopt;

        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void adoptarButton() {

        if (DNIPersona.getText() == "" || DNIPersona.getText().contains(" ")
                || TelefonoPersona.getText() == "" || emailPersona.getText() == ""
                || nombrePersona.getText() == "" || DNIPersona.getText() == null
                || TelefonoPersona.getText() == null || emailPersona.getText() == null
                || nombrePersona.getText() == null) {

            errMess.setText("Ningun campo debe estar vacio");

        } else {

            int num_adopted = this.checkPerson();

            try {
                // Carga el controlador JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establece la conexión con la base de datos
                String url = "jdbc:mysql://localhost:3307/shelterly";
                String usuario = "root";
                String contraseña = "";
                Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

                Statement stmt = conexion.createStatement();

                String query = "UPDATE animal SET exit_date = '" + fechaActual + "' WHERE aid=" + animalito.getId() + ";";

                stmt.executeUpdate(query);

                query = "UPDATE animal SET owner_id = '" + DNIPersona.getText() + "' WHERE aid=" + animalito.getId() + ";";

                stmt.executeUpdate(query);

                query = "UPDATE persona SET animals_adopted = " + (num_adopted + 1) + " WHERE identification='" + DNIPersona.getText() + "';";

                stmt.executeUpdate(query);

                stmt.close();
                conexion.close();
                App.setRoot("secondary");
            } catch (SQLException ex) {
                Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(darBajaAnimalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void volver() {
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(darBajaAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
