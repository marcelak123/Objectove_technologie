package com.example.geometricshape;

import javafx.scene.paint.Color;

public class MyRectangle extends GeometricShape {

    private double dx = 2;

    public MyRectangle(double x, double y) {
        super(x, y, Color.RED);
        draw();
    }

    @Override
    protected void draw() {
        gc.clearRect(0, 0, 50, 50);
        gc.setFill(farba);
        gc.fillRect(5, 12, 40, 25);
    }

    @Override
    public void move() {
        x += dx;

        if (x <= 0 || x >= 750) {
            dx = -dx;
        }

        setLayoutX(x);
    }
}
