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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author neong
 */
public class AnimalesDePersonaController {

    public static Persona p;

    ArrayList<animal> animalesDePersona = new ArrayList<animal>();

    @FXML
    private Button ModificarAnimal;

    @FXML
    private TableColumn<animal, Integer> ageColumnPersona;

    @FXML
    private Label dni;

    @FXML
    private Label email;

    @FXML
    private TableColumn<animal, Date> entryColumnPersona;

    @FXML
    private TableColumn<animal, Date> exitColumnPersona;

    @FXML
    private TableColumn<animal, Integer> idColumnPersona;

    @FXML
    private TableColumn<animal, String> nameColumnPersona;

    @FXML
    private Label nombre;

    @FXML
    private Label num;

    @FXML
    private TableColumn<animal, String> raceColumnPersona;

    @FXML
    private TableView<animal> tablaAnimalesPersona;

    @FXML
    private Label telefono;

    @FXML
    private TableColumn<animal, String> typeColumnPersona;

    @FXML
    private Button verVcaunas;

    @FXML
    private TableColumn<animal, Float> weightColumnPersona;

    public void getAllAnimalsPersona() {
        try {
            animalesDePersona.clear();
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
                String owner_ID = rs.getString("owner_id");
                animal a;
                if (owner_ID != null && owner_ID.equalsIgnoreCase(p.getID())) {

                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDePersona.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDePersona.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDePersona.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDePersona.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDePersona.add(a);
                            break;
                    }

                }

            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
        
        //Columnas de todos
        idColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, Integer>("id"));
        nameColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, String>("name"));
        typeColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, String>("type"));

        raceColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, String>("race"));

        ageColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, Integer>("age"));
        weightColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, Float>("weight"));
        entryColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, Date>("entryingDate"));
        exitColumnPersona.setCellValueFactory(new PropertyValueFactory<animal, Date>("leavingDate"));

        ObservableList<animal> listaAnimales = FXCollections.observableArrayList(animalesDePersona);

        tablaAnimalesPersona.setItems(FXCollections.observableArrayList());
        tablaAnimalesPersona.setItems(listaAnimales);

        tablaAnimalesPersona.getSelectionModel().select(0);
        
        
    }

    public void initialize() {
        
        this.getAllAnimalsPersona();
        nombre.setText(p.getNombre());
        email.setText(p.getEmail());
        telefono.setText(p.getNumber());
        dni.setText(p.getID());
        num.setText(""+p.getNum_animales_adoptados());
        
    }

    public void volver() {
        try {
            App.setRoot("secondary");
        } catch (IOException ex) {
            Logger.getLogger(AnimalesDePersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mofificarAnimal() {
        try {
            modificarAnimal.a = tablaAnimalesPersona.getSelectionModel().getSelectedItem();
            App.setRoot("modificarAnimal");

        } catch (Exception e) {
        }
    }

    public void regitroVacunas() {
        try {
            RegistroVacunasController.a = tablaAnimalesPersona.getSelectionModel().getSelectedItem();
            App.setRoot("registroVacunas");
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
