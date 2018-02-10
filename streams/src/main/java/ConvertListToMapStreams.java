import helper.Car;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertListToMapStreams {

    public static Map<Integer, Car> converListToMap(List<Car> cars) {
        validateInputNotNull(cars);
        return cars.stream().collect(Collectors.toMap(c -> c.getId(), c -> c));
    }

    public static Map<Integer, Car> converListToMapUsingMethodReference(List<Car> cars) {
        validateInputNotNull(cars);
        return cars.stream().collect(Collectors.toMap(Car::getId, Function.identity()));
    }

    private static void validateInputNotNull(List<Car> cars) {
        if (cars == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
    }
}
