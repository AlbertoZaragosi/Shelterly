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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author neong
 */
public class addVacunaController {

    public static animal animal;
    ArrayList<Vacuna> vacunasList = new ArrayList();
    
    @FXML
    private Label nombre;
    
    @FXML
    private Label errMess;

    @FXML
    private Button añadir;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ComboBox<Vacuna> vacunas;

    @FXML
    private Button volver;

    public void fillVacunas() {
        try {
            vacunasList.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from vacuna where vanimal_type ='" + animal.getType() + "'");

            while (rs.next()) {
                int vid = rs.getInt("id");
                String vname = rs.getString("vname");
                int vduration = rs.getInt("vduration");
                String vtype = rs.getString("vanimal_type");
                Vacuna a;
                a = new Vacuna(vid, vname, vtype, vduration);
                vacunasList.add(a);

            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }

    public void volver() {
        try {
            App.setRoot("registroVacunas");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initialize() {
        errMess.setText("");
        nombre.setText("vacunas para "+animal.getName());
        this.fillVacunas();
        ObservableList<Vacuna> listaAnimales = FXCollections.observableArrayList(vacunasList);
        vacunas.setItems(listaAnimales);
        vacunas.getSelectionModel().select(0);

    }

    public void addvacunaToAnimal() {
        try {
            
            Vacuna vToAdd = vacunas.getSelectionModel().getSelectedItem();
            Date today = Date.valueOf(LocalDate.now());
            //System.out.println(today);
            String sql = "insert into registroVacunaAnimal values(" + vToAdd.getId() + "," + animal.getId() + ",'" + today + "'," + vToAdd.getDuracionDias() + ");";
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            
            Statement stmt = conexion.createStatement();
            
            stmt.execute(sql);
            
            stmt.close();
            conexion.close();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addVacunaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLIntegrityConstraintViolationException e){
            errMess.setText("Ya has puesto esa vacuna hoy!");
        }catch (SQLException ex) {
            Logger.getLogger(addVacunaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
