package com.example.geometricshape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GeometricShape extends Canvas {

    protected double x, y;
    protected Color farba;
    protected GraphicsContext gc;

    public GeometricShape(double x, double y, Color farba) {
        super(50, 50);

        this.x = x;
        this.y = y;
        this.farba = farba;

        gc = getGraphicsContext2D();

        setLayoutX(x);
        setLayoutY(y);
    }

    protected abstract void draw();

    public abstract void move();

    public void moveDown(double posun) {
        y += posun;
        setLayoutY(y);
    }

    public void setFarba(Color farba) {
        this.farba = farba;
        draw();
    }
}