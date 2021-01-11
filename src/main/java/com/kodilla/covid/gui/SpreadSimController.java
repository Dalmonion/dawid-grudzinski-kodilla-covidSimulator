package com.kodilla.covid.gui;

import com.kodilla.covid.SpreadSimulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class SpreadSimController {
    @FXML
    Pane world;

    @FXML
    Label healthyPeopleText;

    @FXML
    Label sickPeopleText;

    @FXML
    Label curedPeopleText;

    @FXML
    Label deathPeopleText;

    SpreadSimulation simulation;
    Timeline timeline;
    boolean isMoving;
    boolean firstMove = false;
    int peopleCount = 5;

    @FXML
    public void initialize() {
        isMoving = false;
        world.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));

        timeline = new Timeline(new KeyFrame(Duration.millis(17), x -> {
            simulation.move();
            simulation.draw();
            healthyPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(0)));
            sickPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(1)));
            curedPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(2)));
            deathPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(3)));
        }));
    }

    @FXML
    public void start() {
        if (!firstMove) {
            firstMove = true;
            simulation = new SpreadSimulation(world, peopleCount);
        }

        if(!isMoving) {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            isMoving = true;
        }
    }

    @FXML
    public void stop() {
        isMoving = false;
        timeline.stop();

    }

    @FXML
    public void reset() {
        world.getChildren().clear();
        firstMove = true;
        simulation = new SpreadSimulation(world, peopleCount);
        simulation.draw();
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
