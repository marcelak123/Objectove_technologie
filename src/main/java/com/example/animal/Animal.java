package com.example.animal;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Animal extends Canvas {

    protected double x, y;
    protected Color farba;
    protected GraphicsContext gc;

    protected double rychlost = 2;
    protected int smer = 0;

    private boolean pohybujeSa = true;

    protected static final double MIN_X = 0;
    protected static final double MIN_Y = 0;
    protected static final double MAX_X = 750;
    protected static final double MAX_Y = 450;

    public Animal(double x, double y, Color farba) {
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

    public void zmenSmer() {
        smer = (smer + 1) % 4;
    }

    public void zastavAleboSpusti() {
        pohybujeSa = !pohybujeSa;
    }

    public boolean isPohybujeSa() {
        return pohybujeSa;
    }

    protected boolean mozeSaPohybovat() {
        return pohybujeSa;
    }
    public void nastavPohyb(boolean pohyb) {
        pohybujeSa = pohyb;
    }
}
