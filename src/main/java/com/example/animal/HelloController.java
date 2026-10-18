package com.example.animal;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private Pane plocha;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    private final List<Animal> zvierata = new ArrayList<>();
    private final Random random = new Random();

    private boolean pohybZapnuty = true;

    @FXML
    public void initialize() {

        btn1.setOnAction(e -> {
            Carnivore c = new Carnivore(
                    nahodnaX(),
                    nahodnaY()
            );

            c.nastavPohyb(pohybZapnuty);
            pridajZviera(c);
        });

        btn2.setOnAction(e -> {
            Herbivore h = new Herbivore(
                    nahodnaX(),
                    nahodnaY()
            );

            h.nastavPohyb(pohybZapnuty);
            pridajZviera(h);
        });

        btn3.setOnAction(e -> {
            for (Animal a : zvierata) {
                if (a instanceof Carnivore) {
                    ((Carnivore) a).novySmer();
                }
            }
        });

        btn4.setOnAction(e -> {
            pohybZapnuty = !pohybZapnuty;

            for (Animal a : zvierata) {
                a.nastavPohyb(pohybZapnuty);
            }

            btn4.setText(
                    pohybZapnuty ? "Stoj/Chod-H" : "Pokračovať"
            );
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Animal a : zvierata) {
                    a.move();
                }
            }
        };

        timer.start();
    }

    private double nahodnaX() {
        return random.nextInt(701);
    }

    private double nahodnaY() {
        return random.nextInt(401);
    }

    private void pridajZviera(Animal zviera) {
        zvierata.add(zviera);
        plocha.getChildren().add(zviera);
    }
}
