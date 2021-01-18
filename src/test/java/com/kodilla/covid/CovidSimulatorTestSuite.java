package com.kodilla.covid;

import javafx.scene.layout.Pane;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Covid simulator Test Suite")
class CovidSimulatorTestSuite {

    @Test
    void testIsPersonMoving() {
        //Given
        Pane world = new Pane();
        Person person1 = new Person(world);

        double currentXPosition = person1.getXPosition();
        double currentYPosition = person1.getYPosition();

        //When
        person1.move();

        //Then
        assertNotEquals(currentXPosition, person1.getXPosition());
        assertNotEquals(currentYPosition, person1.getYPosition());
    }

    @Test
    void testPersonStateChange() {
        //Given
        Pane world = new Pane();
        Person person1 = new Person(world);

        //When
        person1.setSick();

        //Then
        assertEquals(Person.State.SICK, person1.getState());
        assertEquals(Color.RED, person1.getPersonColor());
    }

    @DisplayName("Test if person will get sick after colliding with infected")

    @Test
    void testPersonGettingSick() {
        //Given
        Pane world = new Pane();
        SpreadSimulation simulation = new SpreadSimulation(world, 200);
        int sickPeople = simulation.getEachTypeCount().get(0);

        //When
        for (int i = 0; i < 10000; i++) {
            simulation.move();
        }

        //Then
        assertNotEquals(sickPeople, simulation.getEachTypeCount().get(0));
    }

}
