package com.example.geometricshape;

import javafx.scene.paint.Color;

public class MySquare extends GeometricShape {

    private double dy = 2;

    public MySquare(double x, double y) {
        super(x, y, Color.GREEN);
        draw();
    }

    @Override
    protected void draw() {
        gc.clearRect(0, 0, 50, 50);
        gc.setFill(farba);
        gc.fillRect(10, 10, 30, 30);
    }

    @Override
    public void move() {
        y += dy;

        if (y <= 0 || y >= 450) {
            dy = -dy;
        }

        setLayoutY(y);
    }
}
