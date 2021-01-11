package com.kodilla.covid;

import com.kodilla.covid.gui.SpreadSimController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Person {

    public static final double VELOCITY = 2.0;

    public static enum State {
        HEALTHY, SICK, CURED, DECEASED
    }

    private double xPosition;
    private double yPosition;
    private double dx; // x axis direction heading
    private double dy; // y axis direction heading
    private State state = State.HEALTHY;
    private Pane world;
    private int radius = 50;
    private Circle circle;
    private Color personColor = Color.GREEN;


    public Person(Pane world) {

        double dirAngle = Math.random() * 2 * Math.PI;
        this.dx = Math.sin(dirAngle);
        this.dy = Math.cos(dirAngle);
        this.world = world;
        this.circle = new Circle(radius, getPersonColor());
        xPosition = circle.getRadius() + Math.random() * (world.getWidth() - 2 * circle.getRadius());
        yPosition = circle.getRadius() + Math.random() * (world.getHeight() - 2 * circle.getRadius());
        circle.setStroke(Color.BLACK);
        world.getChildren().add(circle);
    }

    public void move() {
        xPosition += getXDirection();
        yPosition += getYDirection();
        if (xPosition > (world.getWidth() - circle.getRadius()) || xPosition < circle.getRadius()) bounceX();
        if (yPosition > (world.getHeight() - circle.getRadius()) || yPosition < circle.getRadius()) bounceY();
    }

    public void updatePosition() {
        double tempX = getXPosition() + getXDirection();
        double tempY = getYPosition() + getYDirection();
        if (tempX > (world.getWidth() - circle.getRadius()) || tempX < circle.getRadius()) bounceX();
        if (tempY > (world.getHeight() - circle.getRadius()) || tempY < circle.getRadius()) bounceY();
        move();
        draw();
    }

    public void draw() {
        circle.setTranslateX(getXPosition());
        circle.setTranslateY(getYPosition());
    }

    public double getXPosition() {
        return xPosition;
    }

    public double getYPosition() {
        return yPosition;
    }

    public double getXDirection() {
        return dx;
    }

    public double getYDirection() {
        return dy;
    }

    public void setXDirection(double direction) {
        dx = direction;
    }

    public void setYDirection(double direction) {
        dy = direction;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void bounceX() {
        dx *= -1;
    }

    public void bounceY() {
        dy *= -1;
    }

    public State getState() {
        return state;
    }

    public void setPersonColor(State state) {
        switch (state) {
            case HEALTHY:
                this.personColor = Color.GREEN;
                break;
            case SICK:
                this.personColor = Color.RED;
                break;
            case CURED:
                this.personColor = Color.ORANGE;
                break;
            case DECEASED:
                this.personColor = Color.BLACK;
                break;
            default:
                this.personColor = Color.WHITE;
        }
        circle.setFill(personColor);
    }

    public Color getPersonColor() {
        return this.personColor;
    }

    public void setSick() {
        setState(State.SICK);
    }

    public void setCured() {
        setState(State.CURED);
    }

    public void setDeceased() {
        setState(State.DECEASED);
    }

    public void setState(State state) {
        this.state = state;
        setPersonColor(state);
    }

    public int getRadius() {
        return radius;
    }

    public double distanceBetweenTwo(Person otherPerson) {
        return  (Math.sqrt(Math.pow((this.getXPosition() - otherPerson.getXPosition()), 2) +
                Math.pow((this.getYPosition() - otherPerson.getYPosition()), 2)));
    }

    public void areCollide(Person otherPerson) {
        double distance = distanceBetweenTwo(otherPerson);

        if (distance <= (this.radius + otherPerson.radius)) {
            double tempX = this.getXDirection();
            double tempY = this.getYDirection();

            this.setXDirection(otherPerson.getXDirection());
            this.setYDirection(otherPerson.getYDirection());

            otherPerson.setXDirection(tempX);
            otherPerson.setYDirection(tempY);
        }
    }
}


