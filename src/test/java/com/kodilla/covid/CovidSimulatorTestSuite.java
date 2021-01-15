package com.kodilla.covid;

import javafx.scene.layout.Pane;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

public class CovidSimulatorTestSuite {

    @Test
    void testIsPersonMoving() {
        //Given
        Pane world = new Pane();
        Person person1 = new Person(world);

        double currentXPosition =  person1.getXPosition();
        double currentYPosition =  person1.getYPosition();

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
        assertEquals(person1.getState(), Person.State.SICK);
        assertEquals(person1.getPersonColor(), Color.RED);
    }

}
