module alberto.refugio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens alberto.refugio to javafx.fxml;
    exports alberto.refugio;
}
