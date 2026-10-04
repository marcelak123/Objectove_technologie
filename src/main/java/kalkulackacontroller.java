import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KalkulackaController {

    @FXML
    private Button plus;

    @FXML
    private Button min;

    @FXML
    private Button del;

    @FXML
    private Button krat;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private Label vysledok;

    @FXML
    private void scitaj() {
        double a = Double.parseDouble(tf1.getText());
        double b = Double.parseDouble(tf2.getText());

        vysledok.setText("Výsledok: " + (a + b));

        tf1.clear();
        tf2.clear();
    }

    @FXML
    private void odcitaj() {
        double a = Double.parseDouble(tf1.getText());
        double b = Double.parseDouble(tf2.getText());

        vysledok.setText("Výsledok: " + (a - b));

        tf1.clear();
        tf2.clear();
    }

    @FXML
    private void nasob() {
        double a = Double.parseDouble(tf1.getText());
        double b = Double.parseDouble(tf2.getText());

        vysledok.setText("Výsledok: " + (a * b));

        tf1.clear();
        tf2.clear();
    }

    @FXML
    private void delit() {
        double a = Double.parseDouble(tf1.getText());
        double b = Double.parseDouble(tf2.getText());

        if (b == 0) {
            vysledok.setText("Nulou nie je možné deliť.");
        } else {
            vysledok.setText("Výsledok: " + (a / b));
        }

        tf1.clear();
        tf2.clear();
    }
}
