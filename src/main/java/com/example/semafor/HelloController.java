package com.example.semafor;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class HelloController {

    @FXML
    private Pane root;

    private Semafor s;

    @FXML
    protected void draw() {
        if (s == null) {
            s = new Semafor();
            root.getChildren().add(s);
        }
    }

    @FXML
    protected void changeState() {
        if (s != null) {
            s.zmenStav();
        }
    }

    @FXML
    protected void startTimer() {
        if (s != null) {
            s.start();
        }
    }

    @FXML
    protected void stop() {
        if (s != null) {
            s.stop();
        }
    }
}
