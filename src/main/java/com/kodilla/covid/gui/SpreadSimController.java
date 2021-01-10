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
    boolean isMoving;
    Timeline timeline;

    @FXML
    public void initialize() {
        isMoving = false;
        world.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        simulation = new SpreadSimulation(world, 100);
        timeline = new Timeline(new KeyFrame(Duration.millis(17), x -> {
            simulation.move();
            simulation.draw();
            healthyPeopleText.setText(String.valueOf(simulation.getEachCaseSize().get(0)));
            sickPeopleText.setText(String.valueOf(simulation.getEachCaseSize().get(1)));
            curedPeopleText.setText(String.valueOf(simulation.getEachCaseSize().get(2)));
            deathPeopleText.setText(String.valueOf(simulation.getEachCaseSize().get(3)));
        }));
    }

    @FXML
    public void start() {
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
        simulation = new SpreadSimulation(world, 100);
        simulation.draw();

    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
