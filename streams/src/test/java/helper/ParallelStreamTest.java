package helper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ParallelStreamTest {

    @Test
    void testParallelStream() {
        List<Car> listOfMillionsOfCars = getListOfNIterationsOf5Cars(2000000);
        runInSingleStream(listOfMillionsOfCars);
        runInParallelStreams(listOfMillionsOfCars);
    }

    private void runInParallelStreams(List<Car> listOfMillionsOfCars) {
        long start_time = System.currentTimeMillis();
        System.out.println("Parallel Stream: " + listOfMillionsOfCars.parallelStream().filter(c->c.getName().contains("Name")).count());
        long end_time = System.currentTimeMillis();
        System.out.println("Parallel Stream took:" + (end_time - start_time) + " milliseconds");
    }

    private void runInSingleStream(List<Car> listOfMillionsOfCars) {
        long start_time = System.currentTimeMillis();
        System.out.println("Normal Stream: " + listOfMillionsOfCars.stream().filter(c -> c.getName().contains("Name")).count());
        long end_time = System.currentTimeMillis();
        System.out.println("Normal Stream took:" + (end_time - start_time) + " milliseconds");
    }

    private List<Car> getListOfNIterationsOf5Cars(int n) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cars.add(new Car.Builder().id(i).name("Name1" + i).build());
            cars.add(new Car.Builder().id(i).name("Name2" + i).build());
            cars.add(new Car.Builder().id(i).name("Name3" + i).build());
            cars.add(new Car.Builder().id(i).name("Name4" + i).build());
            cars.add(new Car.Builder().id(i).name("Name5" + i).build());
        }
        return cars;
    }
}
