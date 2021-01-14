package com.kodilla.covid;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Person {

    public enum State {
        HEALTHY, SICK, CURED, DECEASED;
    }

    private double xPosition;
    private double yPosition;
    private float dx; // x axis direction heading
    private float dy; // y axis direction heading
    private State state = State.HEALTHY;
    private Pane world;
    private double radius = 5;
    private Circle circle;
    private Color personColor = Color.GREEN;
    private long deathTimer = 1000;
    private long timeWhenGotSick;
    private double sickTime = 2000;
    private boolean wasSick = false;
    private double speed = 0.0;
    private double deathRate = 1;


    public Person(Pane world) {

        double dirAngle = Math.random() * 2 * Math.PI;
        this.dx = (float) Math.sin(dirAngle);
        this.dy = (float) Math.cos(dirAngle);
        this.world = world;
        this.circle = new Circle(radius, getPersonColor());
        xPosition = (float) (circle.getRadius() + Math.random() * (world.getWidth() - 2 * circle.getRadius()));
        yPosition = (float) (circle.getRadius() + Math.random() * (world.getHeight() - 2 * circle.getRadius()));
        circle.setStroke(Color.BLACK);
        world.getChildren().add(circle);
    }

    public void move() {

        if (this.getState() != State.DECEASED) {
            xPosition += (getXDirection() * speed);
            yPosition += (getYDirection() * speed);
        }

        if((System.currentTimeMillis() - timeWhenGotSick) >= sickTime) {
            if (this.getState() == State.SICK) {
                if (Math.random() < deathRate * 0.01) {
                    this.setDeceased();
                } else {
                    this.setCured();
                }
            }
        }



//        if(this.getState().equals(State.SICK) && ((System.currentTimeMillis() - timeWhenGotSick) >= sickTime - 1)) {
//            if (Math.random() < deathRate * 0.01) {
//                this.setDeceased();
//            }
//
////            if ((System.currentTimeMillis() - timeWhenGotSick) >= deathTimer) {
////                deathTimer += 1000;
////                System.out.println(deathTimer);
////            }
//
//        }

        if (xPosition > (world.getWidth() - circle.getRadius()) || xPosition < circle.getRadius()) bounceX();
        if (yPosition > (world.getHeight() - circle.getRadius()) || yPosition < circle.getRadius()) bounceY();

//        if (this.getXPosition() > world.getWidth()) {
//            this.setXPosition(0);
//        }
//        if (this.getXPosition() < 0) {
//            this.setXPosition(world.getWidth());
//        }
//
//        if (this.getYPosition() > world.getHeight()) {
//            this.setYPosition(0);
//        }
//        if (this.getYPosition() < 0) {
//            this.setYPosition(world.getHeight());
//        }
    }

    public void draw() {
        circle.setTranslateX(getXPosition());
        circle.setTranslateY(getYPosition());
        circle.setRadius(radius);
    }

    public void setMovementSpeed(double speed) {
        this.speed = speed;
    }

    public double getMovementSpeed() {
        return speed;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setSickTime(double time) {
        this.sickTime = time;
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

    public void setXDirection(float direction) {
        dx = direction;
    }

    public void setYDirection(float direction) {
        dy = direction;
    }

    public void setXPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
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
                this.personColor = Color.BLUE;
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
        if (!wasSick) {
            setState(State.SICK);
            timeWhenGotSick = System.currentTimeMillis();
            wasSick = true;
        }
    }

    public void setCured() {
        setState(State.CURED);
    }

    public void setDeceased() {
        this.setState(State.DECEASED);
    }

    public void setState(State state) {
        this.state = state;
        setPersonColor(state);
    }

    public double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(double deathRate) {
        this.deathRate = deathRate;
    }

    public double getRadius() {
        return radius;
    }

    public float distanceBetweenTwo(Person otherPerson) {
        return (float) (Math.sqrt(((this.getXPosition() - otherPerson.getXPosition()) *
                (this.getXPosition() - otherPerson.getXPosition())) +
                ((this.getYPosition() - otherPerson.getYPosition()) *
                        (this.getYPosition() - otherPerson.getYPosition()))));
    }

    public void areCollide(Person otherPerson) {
        double distance = distanceBetweenTwo(otherPerson);


        if (distance < (this.getRadius() + otherPerson.getRadius())) {

            float tempX = (float) this.getXDirection();
            float tempY = (float) this.getYDirection();

            this.setXDirection((float) otherPerson.getXDirection());
            this.setYDirection((float) otherPerson.getYDirection());

            otherPerson.setXDirection(tempX);
            otherPerson.setYDirection(tempY);
        }
    }
}


