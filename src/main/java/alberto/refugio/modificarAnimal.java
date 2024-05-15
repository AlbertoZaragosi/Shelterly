/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

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
import javafx.scene.control.TextField;

/**
 *
 * @author neong
 */
public class modificarAnimal {

    public static animal a;

    @FXML
    private ChoiceBox<String> RaceComboBox;

    @FXML
    private Button Volver;

    @FXML
    private TextField age;

    @FXML
    private Button altaButton;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox<String> typeComboBox;

    @FXML
    private TextField weight;

    @FXML
    public void initialize() {
        name.setText(a.getName());
        age.setText("" + a.getAge());
        weight.setText("" + a.getWeight());
        ObservableList<String> listaAnimales = FXCollections.observableArrayList();
        listaAnimales.add("dog");
        listaAnimales.add("cat");
        listaAnimales.add("bird");
        listaAnimales.add("reptil");
        listaAnimales.add("roudent");

        typeComboBox.setItems(listaAnimales);
        typeComboBox.getSelectionModel().select(a.getType());

        typeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                fillRazas(a.getType());
            }
        });
        fillRazas(a.getType());
    }

    public void fillRazas(String n) {

        ObservableList<String> listaRazas = FXCollections.observableArrayList();

        switch (n) {
            case "dog":
                listaRazas.addAll("Meztizo", "Labrador Retriever", "Bulldog", "Golden Retriever", "Beagle", "German Shepherd", "Poodle", "Boxer", "Siberian Husky", "Dachshund", "Shih Tzu", "Rottweiler", "Yorkshire Terrier", "Great Dane", "Pomeranian", "Chihuahua", "Border Collie", "Doberman Pinscher", "Australian Shepherd", "Cocker Spaniel", "French Bulldog", "Shetland Sheepdog", "Basset Hound", "Bernese Mountain Dog", "Cavalier King Charles Spaniel", "Maltese", "English Mastiff", "Shiba Inu", "Weimaraner", "Pug", "Miniature Schnauzer", "Boston Terrier", "Vizsla", "Papillon", "Alaskan Malamute", "Collie", "Staffordshire Bull Terrier", "Rhodesian Ridgeback", "Bichon Frise", "Bull Terrier", "West Highland White Terrier", "Schnauzer", "Akita", "Pekingese", "Newfoundland", "Old English Sheepdog", "English Springer Spaniel", "Lhasa Apso", "Havanese");
                break;
            case "cat":
                listaRazas.addAll("Meztizo", "Siamés", "Persa", "Maine Coon", "Ragdoll", "Bengal", "Sphynx", "British Shorthair", "Abyssinian", "Scottish Fold", "Russian Blue", "Manx", "Siamese", "Burmese", "American Shorthair", "Norwegian Forest Cat", "Himalayan", "American Curl", "Exotic Shorthair", "Japanese Bobtail", "Birman", "Bombay", "Somali", "Egyptian Mau", "Ragamuffin");
                break;
            case "bird":
                listaRazas.addAll("Canario", "Periquito", "Agapornis", "Loro", "Cacatúa", "Ninfa", "Diamante mandarín", "Jilguero", "Papagayo", "Tórtola", "Cotorra", "Pinzón");
                break;
            case "reptil":
                listaRazas.addAll("Boa constrictor", "Pitón real", "Cobra", "Serpiente de maíz", "Serpiente del maíz", "Serpiente rey de California", "Lagarto verde", "Lagarto de agua", "Dragón barbudo", "Lagarto monitor", "Iguana verde", "Lagarto skink", "Tortuga de orejas rojas", "Tortuga sulcata", "Tortuga rusa", "Tortuga mora", "Tortuga leopardo", "Tortuga de caja", "Cocodrilo del Nilo", "Caimán de anteojos", "Cocodrilo americano", "Caimán negro", "Cocodrilo de agua salada", "Caimán de Schneider", "Geckos", "Camaleones", "Tuátaras", "Anolis", "Serpientes de cristal", "Serpientes de coral");
                break;
            case "roudent":
                listaRazas.addAll("Hamster sirio", "Hamster enano ruso", "Hamster dorado", "Cobaya de pelo corto", "Cobaya de pelo largo", "Ratón doméstico", "Rata dumbo", "Jerbo de Mongolia", "Conejillo de Indias", "Degu", "Hámster roborowski", "Rata siamesa", "Jerbo pigmeo egipcio", "Hámster chino", "Chinchilla", "Rata almizclera", "Rata de laboratorio", "Hámster de Campbell", "Hámster de Djungarian", "Hámster blanco de invierno", "Hámster siberiano", "Hámster de Campbell enano");
                break;

        }

        RaceComboBox.setItems(listaRazas);
        RaceComboBox.getSelectionModel().select(a.getRace());

    }

    public void volver() {
        try {
            App.setRoot("Secondary");
        } catch (IOException ex) {
            Logger.getLogger(modificarUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar() {
        String nombre = name.getText();
        int edad = Integer.parseInt(age.getText());
        float peso = Float.parseFloat(weight.getText());
        String tipo = typeComboBox.getSelectionModel().getSelectedItem();
        String raza = RaceComboBox.getSelectionModel().getSelectedItem();

        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            String query = "UPDATE animal set aname = '" + nombre + "', atype='" + tipo + "' , arace='" + raza + "' ,aage=" + edad + ", aweight=" + peso + " where aid='"+a.getId()+"';";
            stmt.executeUpdate(query);

            stmt.close();
            conexion.close();
            App.setRoot("secondary");
        } catch (SQLException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaAnimal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(modificarAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
