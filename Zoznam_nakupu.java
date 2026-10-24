
package com.example.zoznam_nakupu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class Zoznam_nakupu {

    @FXML
    private ListView<Polozka> list;
    @FXML
    private TextField co;
    @FXML
    private TextField ci;
    @FXML
    private Button bt1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Label pc;
    private final ObservableList<Polozka> zoznam = FXCollections.observableArrayList();
    @FXML

    public void initialize() {
        list.setItems(zoznam);
        btn2.setDisable(true);
        btn3.setDisable(true);
        list.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    boolean selected = newValue != null;
                    btn2.setDisable(!selected);
                    btn3.setDisable(!selected);
                    if (selected) {
                        co.setText(newValue.getNazov());
                        ci.setText(String.valueOf(newValue.getPocet()));
                    }
                });

        list.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removed();
            }
        });

        updateCount();
    }

    @FXML
    protected void add() {
        String nazov = co.getText().trim();
        int pocet = getPocet();

        if (nazov.isEmpty() || pocet <= 0) {
            return;
        }

        Polozka polozka = new Polozka(nazov, pocet);
        zoznam.add(polozka);

        clearFields();
        updateCount();
    }

    @FXML
    protected void removed() {
        int index = list.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            zoznam.remove(index);
            clearFields();
            updateCount();
        }
    }

    @FXML
    protected void update() {
        int index = list.getSelectionModel().getSelectedIndex();

        if (index == -1) {
            return;
        }

        String nazov = co.getText().trim();
        int pocet = getPocet();

        if (nazov.isEmpty() || pocet <= 0) {
            return;
        }

        Polozka polozka = zoznam.get(index);
        polozka.setNazov(nazov);
        polozka.setPocet(pocet);

        list.refresh();
        updateCount();
        clearFields();
    }

    private int getPocet() {
        try {
            return Integer.parseInt(ci.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void updateCount() {
        int celkom = 0;

        for (Polozka polozka : zoznam) {
            celkom += polozka.getPocet();
        }

        pc.setText("Celkový počet kusov: " + celkom);
    }

    private void clearFields() {
        list.getSelectionModel().clearSelection();
        co.clear();
        ci.clear();
        co.requestFocus();
    }
}

