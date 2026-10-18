package com.example.geometricshape;

import javafx.scene.paint.Color;

public class MyCircle extends GeometricShape {

    private double uhol = 0;
    private double stredX;
    private double stredY;
    private final double polomer = 35;

    public MyCircle(double x, double y) {
        super(x, y, Color.RED);

        stredX = x;
        stredY = y;

        draw();
    }

    @Override
    protected void draw() {
        gc.clearRect(0, 0, 50, 50);
        gc.setFill(farba);
        gc.fillOval(10, 10, 30, 30);
    }

    @Override
    public void move() {
        uhol += 0.04;

        x = stredX + polomer * Math.cos(uhol);
        y = stredY + polomer * Math.sin(uhol);

        setLayoutX(x);
        setLayoutY(y);
    }

    @Override
    public void moveDown(double posun) {
        stredY += posun;
        y += posun;
        setLayoutY(y);
    }
}
