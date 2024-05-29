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
    ArrayList<animal> animalesIDOS = new ArrayList();
    ArrayList<animal> animalesDISPO = new ArrayList();
    ArrayList<Persona> adoptantes = new ArrayList();

    @FXML
    private Button BajaUser;

    @FXML
    private Button ChangeToAnimal;

    @FXML
    private Button Logout;

    @FXML
    private Button vacunaBoton;

    @FXML
    private Pane Main;

    @FXML
    private Button ModificarButton;

    @FXML
    private Pane panePersonas;

    @FXML
    private Button ModificarUser;

    @FXML
    private TableColumn<animal, Integer> ageColumn;

    @FXML
    private Button altaButton;

    @FXML
    private Pane animalsPane;

    @FXML
    private Button bajaButton;

    @FXML
    private Button TODOS;

    @FXML
    private Button AVALIABLE;

    @FXML
    private Button IDOS;

    @FXML
    private Button changeToUsers;

    @FXML
    private Button darAltaUser;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<animal, Date> entryColumn;

    @FXML
    private TableColumn<animal, Integer> idColumn;

    @FXML
    private TableColumn<animal, String> nameColumn;

    @FXML
    private TableColumn<animal, String> raceColumn;

    @FXML
    private TableView<animal> tablaAnimales;

    @FXML
    private TableView<User> tablaUsers;

    @FXML
    private TableColumn<animal, String> typeColumn;

    @FXML
    private TableColumn<User, Integer> userageColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, Boolean> userrootColumn;

    @FXML
    private TableColumn<animal, Date> exitColumn;

    @FXML
    private Pane usersPane;

    @FXML
    private TableColumn<animal, Float> weightColumn;

    @FXML
    private TableView<animal> tablaAnimalesIDOS;

    @FXML
    private TableColumn<animal, Integer> idColumnIDOS;

    @FXML
    private TableColumn<animal, String> nameColumnIDOS;

    @FXML
    private TableColumn<animal, String> typeColumnIDOS;

    @FXML
    private TableColumn<animal, String> raceColumnIDOS;

    @FXML
    private TableColumn<animal, Integer> ageColumnIDOS;

    @FXML
    private TableColumn<animal, Float> weightColumnIDOS;

    @FXML
    private TableColumn<animal, Date> entryColumnIDOS;

    @FXML
    private TableColumn<animal, Date> exitColumnIDOS;

    @FXML
    private TableView<animal> tablaAnimalesAvaliable;

    @FXML
    private TableColumn<animal, Integer> idColumnAV;

    @FXML
    private TableColumn<animal, String> nameColumnAV;

    @FXML
    private TableColumn<animal, String> typeColumnAV;

    @FXML
    private TableColumn<animal, String> raceColumnAV;

    @FXML
    private TableColumn<animal, Integer> ageColumnAV;

    @FXML
    private TableColumn<animal, Float> weightColumnAV;

    @FXML
    private TableColumn<animal, Date> entryColumnAV;

    @FXML
    private TableView<Persona> tablaPersonas;

    @FXML
    private TableColumn<Persona, String> namePersonaColumm;

    @FXML
    private TableColumn<Persona, String> emailPersonaColumn;

    @FXML
    private TableColumn<Persona, Integer> animalesPersonaColumn;

    @FXML
    private TableColumn<Persona, String> PhonePersonaColumn;

    @FXML
    private TableColumn<Persona, String> DNIPersonaColumn;

    @FXML
    private Label welcomeMessage;

    @FXML
    private Label errMess;

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
                String owner_ID = rs.getString("owner_id");
                animal a;
                if (owner_ID != null) {
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
                } else {
                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animales.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animales.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animales.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animales.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animales.add(a);
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

        tablaAnimales.getSelectionModel().select(0);
    }

    public void getAllAnimalsIDOS() {
        try {
            animalesIDOS.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from animal where exit_date is not null");

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
                if (owner_ID != null) {
                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesIDOS.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesIDOS.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesIDOS.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesIDOS.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesIDOS.add(a);
                            break;
                    }
                } else {
                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesIDOS.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesIDOS.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesIDOS.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesIDOS.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesIDOS.add(a);
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

        //Columnas de los idos
        idColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, Integer>("id"));
        nameColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, String>("name"));
        typeColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, String>("type"));

        raceColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, String>("race"));

        ageColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, Integer>("age"));
        weightColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, Float>("weight"));
        entryColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, Date>("entryingDate"));

        exitColumnIDOS.setCellValueFactory(new PropertyValueFactory<animal, Date>("leavingDate"));

        ObservableList<animal> listaAnimalesIDOS = FXCollections.observableArrayList(animalesIDOS);

        tablaAnimalesIDOS.setItems(FXCollections.observableArrayList());
        tablaAnimalesIDOS.setItems(listaAnimalesIDOS);

        tablaAnimalesIDOS.getSelectionModel().select(0);
    }

    public void getAllAnimalsAV() {
        try {
            animalesDISPO.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("select * from animal where exit_date is null");

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
                if (owner_ID != null) {
                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDISPO.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDISPO.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDISPO.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDISPO.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date);
                            animalesDISPO.add(a);
                            break;
                    }
                } else {
                    switch (atype) {
                        case "cat":
                            a = new Cat(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesDISPO.add(a);
                            break;
                        case "dog":
                            a = new Dog(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesDISPO.add(a);
                            break;
                        case "reptile":
                            a = new Reptile(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesDISPO.add(a);
                            break;
                        case "rodent":
                            a = new Roudent(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesDISPO.add(a);
                            break;
                        case "bird":
                            a = new Bird(aid, aname, atype, arace, aage, aweight, entry_date, exit_date, owner_ID);
                            animalesDISPO.add(a);
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
        //Columnas de los avaliable
        idColumnAV.setCellValueFactory(new PropertyValueFactory<animal, Integer>("id"));
        nameColumnAV.setCellValueFactory(new PropertyValueFactory<animal, String>("name"));
        typeColumnAV.setCellValueFactory(new PropertyValueFactory<animal, String>("type"));

        raceColumnAV.setCellValueFactory(new PropertyValueFactory<animal, String>("race"));

        ageColumnAV.setCellValueFactory(new PropertyValueFactory<animal, Integer>("age"));
        weightColumnAV.setCellValueFactory(new PropertyValueFactory<animal, Float>("weight"));
        entryColumnAV.setCellValueFactory(new PropertyValueFactory<animal, Date>("entryingDate"));

        ObservableList<animal> listaAnimalesDISPO = FXCollections.observableArrayList(animalesDISPO);

        tablaAnimalesAvaliable.setItems(FXCollections.observableArrayList());
        tablaAnimalesAvaliable.setItems(listaAnimalesDISPO);

        tablaAnimalesAvaliable.getSelectionModel().select(0);

    }

    public void getAllUsers() {
        try {
            usersList.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from users");

            while (rs.next()) {

                String uname = rs.getString("uname");
                //int uage = rs.getInt("uage");
                String uemail = rs.getString("uemail");
                boolean uRoot = rs.getBoolean("isRoot");
                String upass = rs.getString("upassw");
                User u = new User(uname, uemail, uRoot, upass);
                usersList.add(u);

            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }

        //userageColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("age"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        userrootColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isRoot"));

        ObservableList<User> listaUsers = FXCollections.observableArrayList(usersList);
        tablaUsers.setItems(FXCollections.observableArrayList());
        tablaUsers.setItems(listaUsers);

        tablaUsers.getSelectionModel().select(0);
    }

    public void getAllPersonas() {
        try {
            usersList.clear();
            // Using jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conection to db
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from persona");

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                int animals_adopted = rs.getInt("animals_adopted");
                String email = rs.getString("email");
                String phone_number = rs.getString("phone_number");
                String identification = rs.getString("identification");

                Persona p = new Persona(nombre, email, phone_number, identification, animals_adopted);
                adoptantes.add(p);

            }

            rs.close();
            stmt.close();
            conexion.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }

        animalesPersonaColumn.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("num_animales_adoptados"));
        emailPersonaColumn.setCellValueFactory(new PropertyValueFactory<Persona, String>("email"));
        namePersonaColumm.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        PhonePersonaColumn.setCellValueFactory(new PropertyValueFactory<Persona, String>("number"));
        DNIPersonaColumn.setCellValueFactory(new PropertyValueFactory<Persona, String>("ID"));

        ObservableList<Persona> listaPersonas = FXCollections.observableArrayList(adoptantes);
        tablaPersonas.setItems(FXCollections.observableArrayList());
        tablaPersonas.setItems(listaPersonas);

        tablaPersonas.getSelectionModel().select(0);

    }

    public void initialize() {

        //System.out.println(u.toString());
        errMess.setText("");
        welcomeMessage.setText("Bienvenido: " + u.getName());
        this.getAllAnimals();
        this.getAllAnimalsIDOS();
        this.getAllAnimalsAV();
        this.getAllUsers();
        this.getAllPersonas();
        this.showAvaliableAnimales();

        if (u.isRoot) {
            changeToUsers.setDisable(false);
            changeToUsers.setVisible(true);
        } else {
            changeToUsers.setDisable(true);
            changeToUsers.setVisible(false);

        }

        animalsPane.toFront();

    }

    public User getUser() {
        return u;
    }

    public void setUser(User u) {
        this.u = u;
    }

    public void DarAlta() throws IOException {
        App.setRoot("altaAnimal");
    }

    public void DarAltaUser() throws IOException {
        App.setRoot("altaUser");
    }

    public void darBaja() {
        try {
            if (tablaAnimales.isVisible()) {
                darBajaAnimalController.animalito = tablaAnimales.getSelectionModel().getSelectedItem();
                App.setRoot("darBajaAnimal");
                this.getAllAnimals();
                this.getAllAnimalsAV();
                this.getAllAnimalsIDOS();
            } else if (tablaAnimalesAvaliable.isVisible()) {
                darBajaAnimalController.animalito = tablaAnimalesAvaliable.getSelectionModel().getSelectedItem();
                App.setRoot("darBajaAnimal");
                this.getAllAnimals();
                this.getAllAnimalsAV();
                this.getAllAnimalsIDOS();
            } else if (tablaAnimalesIDOS.isVisible()) {
                

            }

        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void darBajaUser() {
        User deletedUser = tablaUsers.getSelectionModel().getSelectedItem();
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();
            String query = "DELETE FROM users WHERE uemail='" + deletedUser.getEmail() + "';";
            if (u.getEmail().equalsIgnoreCase(deletedUser.getEmail())) {

            } else {
                stmt.executeUpdate(query);
            }

            stmt.close();
            conexion.close();
            tablaUsers.getSelectionModel().select(0);
            usersPane.toFront();
        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.getAllUsers();

    }

    public void userPane() {
        usersPane.toFront();
    }

    public void animalsPane() {
        animalsPane.toFront();
    }

    public void Logout() {
        try {
            App.setRoot("primary");
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarUser() {
        try {
            modificarUser.u = tablaUsers.getSelectionModel().getSelectedItem();
            if (tablaUsers.getSelectionModel().getSelectedItem().getEmail().equalsIgnoreCase(u.getEmail())) {
                errMess.setText("ESE USUARIO ESTA SIENDO USADO");
            } else {
                App.setRoot("modificarUser");
            }
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mofificarAnimal() {
        if (tablaAnimales.isVisible()) {
            try {
                modificarAnimal.a = tablaAnimales.getSelectionModel().getSelectedItem();
                App.setRoot("modificarAnimal");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tablaAnimalesIDOS.isVisible()) {
            try {
                modificarAnimal.a = tablaAnimalesIDOS.getSelectionModel().getSelectedItem();
                App.setRoot("modificarAnimal");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tablaAnimalesAvaliable.isVisible()) {
            try {
                modificarAnimal.a = tablaAnimalesAvaliable.getSelectionModel().getSelectedItem();
                App.setRoot("modificarAnimal");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void regitroVacunas() {

        if (tablaAnimales.isVisible()) {
            try {
                RegistroVacunasController.a = tablaAnimales.getSelectionModel().getSelectedItem();
                App.setRoot("registroVacunas");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tablaAnimalesIDOS.isVisible()) {
            try {
                RegistroVacunasController.a = tablaAnimalesIDOS.getSelectionModel().getSelectedItem();
                App.setRoot("registroVacunas");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tablaAnimalesAvaliable.isVisible()) {
            try {
                RegistroVacunasController.a = tablaAnimalesAvaliable.getSelectionModel().getSelectedItem();
                App.setRoot("registroVacunas");
            } catch (IOException ex) {
                Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void showAllAnimales() {
        TODOS.setDisable(true);
        AVALIABLE.setDisable(false);
        IDOS.setDisable(false);
        tablaAnimales.toFront();
        tablaAnimales.setVisible(true);
        tablaAnimalesIDOS.setVisible(false);
        tablaAnimalesAvaliable.setVisible(false);

    }

    public void showIDOSAnimales() {
        TODOS.setDisable(false);
        AVALIABLE.setDisable(false);
        IDOS.setDisable(true);
        tablaAnimalesIDOS.toFront();
        tablaAnimales.setVisible(false);
        tablaAnimalesIDOS.setVisible(true);
        tablaAnimalesAvaliable.setVisible(false);
    }

    public void showAvaliableAnimales() {
        TODOS.setDisable(false);
        AVALIABLE.setDisable(true);
        IDOS.setDisable(false);
        tablaAnimalesAvaliable.toFront();
        tablaAnimales.setVisible(false);
        tablaAnimalesIDOS.setVisible(false);
        tablaAnimalesAvaliable.setVisible(true);
    }

    public void personaPane() {
        panePersonas.toFront();
    }

    public void infoPersona() {
        try {
            AnimalesDePersonaController.p = tablaPersonas.getSelectionModel().getSelectedItem();
            App.setRoot("PersonaAnimal");
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarAdoptante(){
        try {
            modificarAdoptante.p = tablaPersonas.getSelectionModel().getSelectedItem();
            App.setRoot("modificarPersona");
        } catch (IOException ex) {
            Logger.getLogger(SecondaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
