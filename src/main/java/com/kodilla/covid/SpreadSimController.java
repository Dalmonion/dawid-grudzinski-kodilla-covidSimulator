package com.kodilla.covid;

import com.kodilla.covid.SpreadSimulation;
import com.kodilla.covid.Stopwatch;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.text.DecimalFormat;


public class SpreadSimController {
    @FXML
    Pane world;

    @FXML
    Label healthyPeopleText;

    @FXML
    Label healthyPeoplePercentageText;

    @FXML
    Label sickPeopleText;

    @FXML
    Label sickPeoplePercentageText;

    @FXML
    Label curedPeopleText;

    @FXML
    Label curedPeoplePercentageText;

    @FXML
    Label deathPeopleText;

    @FXML
    Label deathPeoplePercentageText;

    @FXML
    Slider peopleSize;

    @FXML
    Slider groupSize;

    @FXML
    Slider movementTime;

    @FXML
    Slider sickTime;

    @FXML
    Slider deathRate;

    @FXML
    Label stopwatch;

    private SpreadSimulation simulation;
    private Timeline timeline;
    private Stopwatch stopwatchObject;
    private boolean isMoving;
    private boolean firstMove = false;
    private int peopleCount = 100;

    @FXML
    public void initialize() {
        isMoving = false;
        world.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
        stopwatchObject = new Stopwatch();


        timeline = new Timeline(new KeyFrame(Duration.millis(17), x -> {

            peopleCount = (int) groupSize.getValue();
            simulation.setPeopleProperties(peopleSize.getValue(), movementTime.getValue(),
                    sickTime.getValue(), deathRate.getValue());

            simulation.draw();
            simulation.move();

            stopwatch.setText(stopwatchObject.getDuration() + " seconds");

            healthyPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(0)));
            healthyPeoplePercentageText.setText(String.valueOf((int) ((float)
                    simulation.getEachTypeCount().get(0) / simulation.getPeopleSize() * 100)));

            sickPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(1)));
            sickPeoplePercentageText.setText(String.valueOf((int) ((float)
                    simulation.getEachTypeCount().get(1) / simulation.getPeopleSize() * 100)));

            curedPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(2)));
            curedPeoplePercentageText.setText(String.valueOf((int) ((float)
                    simulation.getEachTypeCount().get(2) / simulation.getPeopleSize() * 100)));

            deathPeopleText.setText(String.valueOf(simulation.getEachTypeCount().get(3)));
            deathPeoplePercentageText.setText(String.valueOf((int) ((float)
                    simulation.getEachTypeCount().get(3) / simulation.getPeopleSize() * 100)));

        }));
    }

    @FXML
    public void start() {
        if (!firstMove) {
            firstMove = true;
            peopleCount = (int) groupSize.getValue();
            simulation = new SpreadSimulation(world, peopleCount);
            stopwatchObject.start();
        }

        if(!isMoving) {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
            stopwatchObject.start();
            isMoving = true;
        }
    }

    @FXML
    public void stop() {
        isMoving = false;
        timeline.stop();
        stopwatchObject.stop();
    }

    @FXML
    public void reset() {
        world.getChildren().clear();
        firstMove = true;
        simulation = new SpreadSimulation(world, peopleCount);
        simulation.draw();
        stopwatchObject = new Stopwatch();
        stop();
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
