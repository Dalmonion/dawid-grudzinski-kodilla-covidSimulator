package com.kodilla.covid;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SpreadSimulation {


    private ArrayList<Person> people;

    public SpreadSimulation(Pane world, int popSize) {
        people = new ArrayList<>();

        for (int i=0; i < popSize; i++) {
            people.add(new Person(world));
        }
        draw();
        people.get(people.size()-1).setSick();
    }

    public void move() {
        for (Person person : people) {
            person.move();
        }
    }

    public void draw() {
        for (Person person : people) {
            person.draw();
        }
    }


}
