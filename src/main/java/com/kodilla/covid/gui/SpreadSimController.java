package com.kodilla.covid.gui;

import com.kodilla.covid.IOServices;
import com.kodilla.covid.Person;
import com.kodilla.covid.SpreadSimulation;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;


public class SpreadSimController {

    @FXML
    Pane world;
    Person person;
    SpreadSimulation simulation;
    boolean isMoving;
    Timer timer;

    class timeIntervalMoving extends TimerTask {

        @Override
        public void run() {
            person.move();
            person.draw();
        }
    }

    @FXML
    public void initialize() {
        isMoving = false;
        world.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
    }

    @FXML
    public void start() {
        if(!isMoving) {
            timer = new Timer();
            timer.schedule(new SpreadSimController.timeIntervalMoving(), 0, 17);
            isMoving = true;
        }

    }

    @FXML
    public void stop() {
        timer.cancel();
        isMoving = false;

    }

    @FXML
    public void reset() {
        world.getChildren().clear();
        person = new Person(world);
        person.draw();
    }

    @FXML
    public void exit() {

    }
}
