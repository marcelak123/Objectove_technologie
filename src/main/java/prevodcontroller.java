import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrevodController {

    @FXML
    private TextField tf1;
    @FXML
    private Label v1;
    @FXML
    private Label v2;
    @FXML
    private Label v3;
    @FXML
    private Label v4;

    @FXML
    private void preved() {
        double a = Double.parseDouble(tf1.getText());

        double mm = a;
        double cm = a / 10;
        double m = a / 1000;
        double km = a / 1000000;

        v1.setText("mm: " + mm);
        v2.setText("cm: " + cm);
        v3.setText("m: " + m);
        v4.setText("km: " + km);

        tf1.clear();
    }
}
