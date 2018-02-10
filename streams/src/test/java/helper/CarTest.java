package helper;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car.Builder().id(1).name("name").type("type").build();
    }

    @Test
    void getterTest() {
        assertEquals(1, car.getId());
        assertEquals("name", car.getName());
        assertEquals("type", car.getType());
    }

    @Test
    void hashcodeEqualsTest() {
        EqualsVerifier.forClass(Car.class).usingGetClass().verify();
    }

    @Test
    void toStringPrintsInExpectedFormat() {
        String expectedCarToString = "Car{id=1, name='name', type='type'}";
        assertEquals(expectedCarToString, car.toString());
    }
}