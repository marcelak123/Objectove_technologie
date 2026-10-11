package com.example.robot;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class HelloController {

    @FXML
    private Pane p;

    private robot robot;

    @FXML
    public void initialize() {
        robot = new robot(100, 100, p);
        p.getChildren().add(robot);

        p.setFocusTraversable(true);

        p.setOnMouseClicked(e -> {
            if (e.getTarget() == p) {
                p.requestFocus();
            }
        });
    }
}