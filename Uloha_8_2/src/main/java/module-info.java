module com.example.uloha_8_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.uloha_8_2 to javafx.fxml;
    exports com.example.uloha_8_2;
}