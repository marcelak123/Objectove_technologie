import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class KalkulackaController {

    @FXML
    private Label vs;

    private double prveCislo = 0;
    private String operacia = "";
    private boolean noveCislo = true;

    @FXML
    private void cislo(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String cislo = button.getText();

        if (noveCislo || vs.getText().equals("0")) {
            vs.setText(cislo);
            noveCislo = false;
        } else {
            vs.setText(vs.getText() + cislo);
        }
    }

    @FXML
    private void bodka() {
        if (noveCislo) {
            vs.setText("0.");
            noveCislo = false;
        } else if (!vs.getText().contains(".")) {
            vs.setText(vs.getText() + ".");
        }
    }

    @FXML
    private void operacia(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();

        prveCislo = Double.parseDouble(vs.getText());
        operacia = button.getText();

        noveCislo = true;
    }

    @FXML
    private void vypocitaj() {
        double druheCislo = Double.parseDouble(vs.getText());
        double vysledok = 0;

        switch (operacia) {
            case "+":
                vysledok = prveCislo + druheCislo;
                break;

            case "-":
                vysledok = prveCislo - druheCislo;
                break;

            case "*":
                vysledok = prveCislo * druheCislo;
                break;

            case "/":
                if (druheCislo == 0) {
                    vs.setText("Nulou sa deliť nedá");
                    noveCislo = true;
                    return;
                }
                vysledok = prveCislo / druheCislo;
                break;
        }

        vs.setText(String.valueOf(vysledok));
        noveCislo = true;
    }

    @FXML
    private void vymaz() {
        vs.setText("0");
        prveCislo = 0;
        operacia = "";
        noveCislo = true;
    }
}
