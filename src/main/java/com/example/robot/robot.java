package com.example.robot;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class robot extends Canvas {

    private GraphicsContext gc;
    private Pane p;
    private Timeline cas;
    private boolean automat = false;
    private int smerX = 1;
    private int smerY = 0;
    private int rych = 3;

    public robot(double x, double y, Pane p) {
        super(40, 40);
        this.p = p;
        setLayoutX(x);
        setLayoutY(y);
        gc = getGraphicsContext2D();
        vykresli();

        cas = new Timeline(
                new KeyFrame(Duration.millis(30), e -> pohyb()));
        cas.setCycleCount(Animation.INDEFINITE);
        cas.stop();
        setFocusTraversable(true);
        setOnKeyPressed(evt -> spracuj(evt));
        setOnMouseClicked(evt -> klik());
    }

    private void vykresli() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.BLUE);
        gc.fillRect(0, 0, getWidth(), getHeight());

        gc.setFill(Color.YELLOW);

        if (smerX == 1) {
            gc.fillRect(28, 10, 8, 20);
        } else if (smerX == -1) {
            gc.fillRect(4, 10, 8, 20);
        } else if (smerY == -1) {
            gc.fillRect(10, 4, 20, 8);
        } else if (smerY == 1) {
            gc.fillRect(10, 28, 20, 8);
        }
    }

    private void klik() {
        automat = !automat;

        if (automat) {
            cas.play();
        } else {
            cas.stop();
        }

        requestFocus();
    }

    private void pohyb() {
        double novaX = getLayoutX() + smerX * rych;
        double novaY = getLayoutY() + smerY * rych;

        if (novaX <= 0) {
            novaX = 0;
            smerX = 1;
        } else if (novaX + getWidth() >= p.getWidth()) {
            novaX = p.getWidth() - getWidth();
            smerX = -1;
        }

        if (novaY <= 0) {
            novaY = 0;
            smerY = 1;
        } else if (novaY + getHeight() >= p.getHeight()) {
            novaY = p.getHeight() - getHeight();
            smerY = -1;
        }

        setLayoutX(novaX);
        setLayoutY(novaY);

        vykresli();
    }

    private void spracuj(KeyEvent evt) {
        KeyCode k = evt.getCode();

        if (k == KeyCode.W) {
            smerX = 0;
            smerY = -1;
        } else if (k == KeyCode.S) {
            smerX = 0;
            smerY = 1;
        } else if (k == KeyCode.A) {
            smerX = -1;
            smerY = 0;
        } else if (k == KeyCode.D) {
            smerX = 1;
            smerY = 0;
        } else {
            return;
        }

        vykresli();
        if (!automat) {
            pohyb();
        }
    }
}