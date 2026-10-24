
package com.example.appcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class AppController {

    @FXML
    private ListView<String> list;
    @FXML
    private TextField name;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    private final ObservableList<String> zoznam = FXCollections.observableArrayList();

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
                        name.setText(newValue);
                    }
                });

        list.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removed();
            }
        });

        btn1.setOnAction(event -> add());
        btn2.setOnAction(event -> removed());
        btn3.setOnAction(event -> update());
    }

    @FXML
    protected void add() {
        String item = name.getText().trim();
        if (!item.isEmpty()) {
            zoznam.add(item);
            name.clear();
            name.requestFocus();
        }
    }

    @FXML
    protected void removed() {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            zoznam.remove(selectedIndex);
            name.clear();
        }
    }

    @FXML
    protected void update() {
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        String item = name.getText().trim();
        if (selectedIndex != -1 && !item.isEmpty()) {
            zoznam.set(selectedIndex, item);
            name.clear();
            name.requestFocus();
        }
    }
}

