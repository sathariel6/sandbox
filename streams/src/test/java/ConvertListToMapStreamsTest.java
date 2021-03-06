import helper.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConvertListToMapStreamsTest {

    private List<Car> cars;
    private Car car, car2, car3;

    @BeforeEach
    void setUp() {
        car = new Car.Builder().id(1).name("Porsche").type("Sports").build();
        car2 = new Car.Builder().id(2).name("Renault").type("Don'tEvenKnow").build();
        car3 = new Car.Builder().id(3).name("Truck").type("Truck").build();

        cars = buildListOfCars();
    }

    @Test
    void listOfObjectsMapsToMap() {
        Map<Integer, Car> mapOfCars = ConvertListToMapStreams.convertListToMap(cars);

        assertEquals(expectedMap(), mapOfCars);
    }

    @Test
    void listOfObjectsMapsToMapUsingMethodReference() {
        Map<Integer, Car> mapOfCars = ConvertListToMapStreams.convertListToMapUsingMethodReference(cars);

        assertEquals(expectedMap(), mapOfCars);
    }

    @Test
    void methodDoesNotAllowPassingInNullList() {
        cars = null;
        assertThrows(IllegalArgumentException.class, () -> ConvertListToMapStreams.convertListToMap(cars));
        assertThrows(IllegalArgumentException.class, () -> ConvertListToMapStreams.convertListToMapUsingMethodReference(cars));
    }

    @Test
    void mappingFailsForDuplicateKeys() {
        cars.add(new Car.Builder().id(3).name("Duplicate").build());
        assertThrows(IllegalStateException.class, () -> ConvertListToMapStreams.convertListToMap(cars));
        assertThrows(IllegalStateException.class, () -> ConvertListToMapStreams.convertListToMapUsingMethodReference(cars));
    }

    private Map<Integer, Car> expectedMap() {
        Map<Integer, Car> cars = new HashMap<>();
        cars.put(1, car);
        cars.put(2, car2);
        cars.put(3, car3);
        return cars;
    }

    private List<Car> buildListOfCars() {
        Car car = new Car.Builder().id(1).name("Porsche").type("Sports").build();
        Car car2 = new Car.Builder().id(2).name("Renault").type("Don'tEvenKnow").build();
        Car car3 = new Car.Builder().id(3).name("Truck").type("Truck").build();
        List<Car> carsList = new ArrayList<>();
        carsList.add(car);
        carsList.add(car2);
        carsList.add(car3);
        return carsList;
    }
}