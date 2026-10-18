package com.example.animal;

import javafx.scene.paint.Color;

public class Herbivore extends Animal {

    public Herbivore(double x, double y) {
        super(x, y, Color.GREEN);
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
        if (!mozeSaPohybovat()) {
            return;
        }

        switch (smer) {
            case 0:
                x += rychlost;
                if (x >= MAX_X) {
                    x = MAX_X;
                    smer = 1;
                }
                break;

            case 1:
                y += rychlost;
                if (y >= MAX_Y) {
                    y = MAX_Y;
                    smer = 2;
                }
                break;

            case 2:
                x -= rychlost;
                if (x <= MIN_X) {
                    x = MIN_X;
                    smer = 3;
                }
                break;

            case 3:
                y -= rychlost;
                if (y <= MIN_Y) {
                    y = MIN_Y;
                    smer = 0;
                }
                break;
        }

        setLayoutX(x);
        setLayoutY(y);
    }
}
