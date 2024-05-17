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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author neong
 */
public class RegistroVacunasController {
    
    public static animal a;

    ArrayList<RegistroVacuna> registro = new ArrayList();
    ArrayList<String> ids = new ArrayList();

    @FXML
    private Button addVaccine;

    @FXML
    private ComboBox<String> animalId;

    @FXML
    private Label animalName;

    @FXML
    private TableColumn<RegistroVacuna, Integer> durationColumn;

    @FXML
    private TableColumn<RegistroVacuna, Date> fechaColumn;

    @FXML
    private TableColumn<RegistroVacuna, Integer> idColumn;

    @FXML
    private TableColumn<RegistroVacuna, Date> nextColumn;

    @FXML
    private Label raza;

    @FXML
    private TableView<RegistroVacuna> tablaVacunas;

    @FXML
    private Label tipo;

    @FXML
    public void initialize() {
        this.fillRegistro();
        this.fillIds();
        idColumn.setCellValueFactory(new PropertyValueFactory<RegistroVacuna, Integer>("id"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<RegistroVacuna, Date>("fechaVacunacion"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<RegistroVacuna, Integer>("duracion"));
        nextColumn.setCellValueFactory(new PropertyValueFactory<RegistroVacuna, Date>("nextVacuna"));

        ObservableList<RegistroVacuna> listavacunas = FXCollections.observableArrayList(registro);

        tablaVacunas.setItems(listavacunas);
        
        animalId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                initialize();
            }
        });
        
        tablaVacunas.getSelectionModel().select(0);
        

    }

    public void fillRegistro() {
        try {
            registro.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from registroVacunaAnimal where aid = "+a.getId()+";");

            while (rs.next()) {
                int vid = rs.getInt("id_vacuna");
                int aid = rs.getInt("aid");
                Date fechaVacunacion = rs.getDate("fecha_de_vacunacion");
                int dur = rs.getInt("duracion");
                RegistroVacuna r = new RegistroVacuna(vid,aid,fechaVacunacion,dur);
                registro.add(r);
            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }

    public void fillIds(){
        try {
            ids.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from animal order by aid");

            while (rs.next()) {
                int aid = rs.getInt("aid");
                String aname = rs.getString("aname");
                int aage = rs.getInt("aage");
                float aweight = rs.getFloat("aweight");
                String atype = rs.getString("atype");
                String arace = rs.getString("arace");
                Date entry_date = rs.getDate("entry_date");
                Date exit_date = rs.getDate("exit_date");
                animal a;
                switch (atype) {
                    case "cat":
                        a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        ids.add(a.getId()+"");
                        break;
                    case "dog":
                        a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        ids.add(a.getId()+"");
                        break;
                    case "reptile":
                        a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        ids.add(a.getId()+"");
                        break;
                    case "rodent":
                        a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        ids.add(a.getId()+"");
                        break;
                    case "bird":
                        a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        ids.add(a.getId()+"");
                        break;
                }

            }
            animalId.getSelectionModel().select(this.a.getId()+"");
            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }
    
    public void addVacuna(){
        try {
            App.setRoot("next");
        } catch (IOException ex) {
            Logger.getLogger(RegistroVacunasController.class.getName()).log(Level.SEVERE, null, ex);
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
