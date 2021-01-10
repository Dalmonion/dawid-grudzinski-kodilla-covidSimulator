package com.kodilla.covid;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SpreadSimulation {


    private ArrayList<Person> people;

    public SpreadSimulation(Pane world, int peopleCount) {
        people = new ArrayList<>();

        for (int i=0; i < peopleCount; i++) {
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

    public List<Integer> getEachCaseSize() {
        List<Integer> peopleDivision = new ArrayList<>();

        for (int i = 0 ; i < 4; i++) {
            peopleDivision.add(0);
        }

        /*
        [0] - people healthy
        [1] - people sick
        [2] - people cured
        [3] - people deaths
         */

        for (Person person : people) {

            switch (person.getState()) {
                case HEALTHY:
                    peopleDivision.set(0, peopleDivision.get(0) + 1);
                    break;
                case SICK:
                    peopleDivision.set(1, peopleDivision.get(1) + 1);
                    break;
                case CURED:
                    peopleDivision.set(2, peopleDivision.get(2) + 1);
                    break;
                case DECEASED:
                    peopleDivision.set(3, peopleDivision.get(3) + 1);
                    break;
            }
        }
        return peopleDivision;
    }


}
