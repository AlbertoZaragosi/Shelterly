/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

/**
 *
 * @author neong
 */
import static alberto.refugio.PrimaryController.encryptPassword;
import java.io.IOException;
import javafx.fxml.FXML;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SecondaryController {

    public static User u;
    User user;
    ArrayList<User> usersList = new ArrayList();
    ArrayList<animal> animales = new ArrayList();

    @FXML
    private Button Logout;

    @FXML
    private Pane Main;

    @FXML
    private TableColumn<animal, Integer> ageColumn;

    @FXML
    private Pane alta;

    @FXML
    private Button altaButton;

    @FXML
    private Pane baja;

    @FXML
    private Button bajaButton;

    @FXML
    private TableColumn<animal, Date> entryColumn;

    @FXML
    private TableColumn<animal, Date> exitColumn;

    @FXML
    private TableColumn<animal, Integer> idColumn;

    @FXML
    private Button managementButton;

    @FXML
    private TableColumn<animal, String> nameColumn;

    @FXML
    private TableColumn<animal, String> raceColumn;

    @FXML
    private Pane search;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<animal> tablaAnimales;

    @FXML
    private TableColumn<animal, String> typeColumn;

    @FXML
    private Pane users;

    @FXML
    private TableColumn<animal, Float> weightColumn;

    @FXML
    private Label welcomeMessage;

    public void getAllAnimals() {
        try {
            animales.clear();
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
                        animales.add(a);
                        break;
                    case "dog":
                        a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        animales.add(a);
                        break;
                    case "reptile":
                        a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        animales.add(a);
                        break;
                    case "rodent":
                        a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        animales.add(a);
                        break;
                    case "bird":
                        a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                        animales.add(a);
                        break;
                }

            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }

    public void initialize() {
        //System.out.println(u.toString());
        welcomeMessage.setText(welcomeMessage.getText() + " " + u.getName());
        this.getAllAnimals();
        idColumn.setCellValueFactory(new PropertyValueFactory<animal, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<animal, String>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<animal, String>("type"));
        
        raceColumn.setCellValueFactory(new PropertyValueFactory<animal, String>("race"));
        
        ageColumn.setCellValueFactory(new PropertyValueFactory<animal, Integer>("age"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<animal, Float>("weight"));
        entryColumn.setCellValueFactory(new PropertyValueFactory<animal, Date>("entryingDate"));
        exitColumn.setCellValueFactory(new PropertyValueFactory<animal, Date>("leavingDate"));

        
        
        ObservableList<animal> listaAnimales = FXCollections.observableArrayList(animales);
       
        
        tablaAnimales.setItems(FXCollections.observableArrayList());
        tablaAnimales.setItems(listaAnimales); 
        if (u.isRoot) {
            managementButton.setDisable(false);
            managementButton.setVisible(true);
        } else {
            managementButton.setDisable(true);
            managementButton.setVisible(false);

        }
        tablaAnimales.getSelectionModel().select(0); 

    }

    public User getUser() {
        return u;
    }

    public void setUser(User u) {
        this.u = u;
    }
    
    public void DarAlta() throws IOException{
        App.setRoot("altaAnimal"); 
    }
    
    public void darBaja(){
        animal deletedAnimal = tablaAnimales.getSelectionModel().getSelectedItem();
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "DELETE FROM animal WHERE aid="+deletedAnimal.getId()+";";
            
            stmt.executeUpdate(query);
            

            stmt.close();
            conexion.close();
            tablaAnimales.getSelectionModel().select(0);
            this.initialize();
        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    }
}
