package com.example.geometricshape;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Pane plocha;

    @FXML
    private Button btnRectangle;

    @FXML
    private Button btnCircle;

    @FXML
    private Button btnSquare;

    @FXML
    private Button btnBlue;

    @FXML
    private Button btnDown;

    private final List<GeometricShape> tvary = new ArrayList<>();
    private final Random random = new Random();

    @FXML
    public void initialize() {

        btnRectangle.setOnAction(e ->
                pridajTvar(new MyRectangle(
                        random.nextInt(plocha.getWidth() > 50
                                ? (int) plocha.getWidth() - 50 : 1),
                        random.nextInt(plocha.getHeight() > 50
                                ? (int) plocha.getHeight() - 50 : 1)
                ))
        );

        btnCircle.setOnAction(e ->
                pridajTvar(new MyCircle(
                        random.nextInt(plocha.getWidth() > 50
                                ? (int) plocha.getWidth() - 50 : 1),
                        random.nextInt(plocha.getHeight() > 50
                                ? (int) plocha.getHeight() - 50 : 1)
                ))
        );

        btnSquare.setOnAction(e ->
                pridajTvar(new MySquare(
                        random.nextInt(plocha.getWidth() > 50
                                ? (int) plocha.getWidth() - 50 : 1),
                        random.nextInt(plocha.getHeight() > 50
                                ? (int) plocha.getHeight() - 50 : 1)
                ))
        );

        btnBlue.setOnAction(e -> {
            for (GeometricShape tvar : tvary) {
                if (tvar instanceof MyCircle) {
                    tvar.setFarba(Color.BLUE);
                }
            }
        });

        btnDown.setOnAction(e -> {
            for (GeometricShape tvar : tvary) {
                tvar.moveDown(30);
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (GeometricShape tvar : tvary) {
                    tvar.move();
                }
            }
        };

        timer.start();
    }

    private void pridajTvar(GeometricShape tvar) {
        tvary.add(tvar);
        plocha.getChildren().add(tvar);
    }
}