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
import javafx.scene.layout.Pane;

public class RegisterController {

    @FXML
    private Button Register;
    
    @FXML
    private Pane panel;
    
    @FXML
    private Pane panel2;

    @FXML
    private TextField uAge;

    @FXML
    private TextField uEmail;

    @FXML
    private PasswordField uPassw;

    @FXML
    private TextField uname;
    
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
        try {
            // Carga el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establece la conexión con la base de datos
            String url = "jdbc:mysql://localhost:3307/shelterly";
            String usuario = "root";
            String contraseña = "";
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            Statement stmt = conexion.createStatement();

            User u = new User(uname.getText(),uEmail.getText(),Integer.parseInt(uAge.getText()),encryptPassword(uPassw.getText()));
            String query = "insert into users (uname,uemail,uage,upassw) values('"+u.getName()+"','"+u.getEmail()+"',"+u.getAge()+",'"+u.getPassword()+"');";
            
            stmt.executeUpdate(query);
            

            stmt.close();
            conexion.close();
            panel.toFront();
            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones, por ejemplo, mostrar un mensaje de error
        }
    }
    
    public void initialize(){
        panel2.toFront();
    }

    /*
    public void Register() {
        //rlou ovzq buxu nzdq

        String correoEnvia = "shelterlynotify@gmail.com";
        String contraseña = "rlouovzqbuxunzdq";
        int num1 = (int) (Math.random() * 9);
        int num2 = (int) (Math.random() * 9);
        int num3 = (int) (Math.random() * 9);
        int num4 = (int) (Math.random() * 9);
        String msg = "" + num1 + num2 + num3 + num4;
        System.out.println("AQUI SI 1");
        Properties objPEC = new Properties();

        objPEC.put("mail.smtp.host", "smtp.gmail.com");
        objPEC.setProperty("mail.smtp.starttls.enable", "true");
        objPEC.put("mail.smtp.port", "586");
        objPEC.setProperty("mail.smtp.port", "586");
        objPEC.put("mail.smtp.user", uEmail.getText());
        objPEC.setProperty("mail.smtp.auth", "true");
        System.out.println("AQUI SI 2");

        Session sesion = Session.getDefaultInstance(objPEC);
        MimeMessage mail = new MimeMessage(sesion);
        System.out.println("AQUI SI 3");

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(uEmail.getText()));
            mail.setSubject(msg);
            mail.setText(msg);
        System.out.println("AQUI SI 4");

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contraseña);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
        System.out.println("AQUI SI 5");

            System.out.println("ENVIADO");
        } catch (Exception e) {
            System.err.println("ENVIADO");
            e.printStackTrace();
        }

    }
     */
}
