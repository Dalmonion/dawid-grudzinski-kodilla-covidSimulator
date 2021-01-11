package com.kodilla.covid;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SpreadSimulation {


    private ArrayList<Person> people;

    public SpreadSimulation(Pane world, int peopleCount) {
        people = new ArrayList<>();


        for ( int i = 0 ; i < peopleCount; i++) {
            people.add(new Person(world));
        }

//        people.add(new Person(world));
//        System.out.println("first person location X: "  + people.get(0).getXPosition() + ", Y: " +
//                people.get(0).getYPosition());
//
//        while (people.size() < peopleCount) {
//            Person newPerson = new Person(world);
//            System.out.println("newPerson location X: " + newPerson.getXPosition() + ", Y: " +
//                    newPerson.getYPosition());
//            for (int j = 0; j < people.size(); j++) {
//
//                double getDistance = newPerson.distanceBetweenTwo(people.get(j));
//                System.out.println("Distance = " + getDistance);
//
//                if (getDistance <= (newPerson.getRadius() + people.get(j).getRadius())) {
//                    System.out.println("Too close");
//                    System.out.println();
//                    break;
//                }
//
//                if (j == (people.size() - 1)) {
//                    people.add(newPerson);
//                    System.out.println("New person added with location X: " + newPerson.getXPosition() + ", Y: " +
//                    newPerson.getYPosition());
//                    System.out.println();
//                }
//
//            }
//        }
        System.out.println(people.size());
        people.get(people.size() - 1).setSick();


    }

    public void move() {
        for (Person person : people) {
            for (Person otherPersons : people) {
                if (person.equals(otherPersons)) continue;
//                    person.areCollide(otherPersons);
                    double distance = person.distanceBetweenTwo(otherPersons);
                    if (distance < person.getRadius() + otherPersons.getRadius()) {
                        if (person.getState().equals(Person.State.SICK)) {
                            otherPersons.setSick();
                        } else if (otherPersons.getState().equals(Person.State.SICK)) {
                            person.setSick();
                        } else {

                        }
                    }
            }
            person.move();
        }

    }

    public void draw() {
        for (Person person : people) {
            person.draw();
        }
    }

    public List<Integer> getEachTypeCount() {
        List<Integer> peopleDivision = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
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
