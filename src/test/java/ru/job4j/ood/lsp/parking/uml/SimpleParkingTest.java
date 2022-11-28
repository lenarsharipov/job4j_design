package ru.job4j.ood.lsp.parking.uml;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class SimpleParkingTest {

    @Test
    public void whenTruckSizeLessTwoThenException() {
        assertThrows(IllegalArgumentException.class, () -> {
                Vehicle truck = new Truck(1);
        });
    }

    @Test
    public void whenAddTwoCarsAndTruckThenAllAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle car = new Car();
        Vehicle car2 = new Car();
        Vehicle truck = new Truck(2);
        assertTrue(parking.add(car));
        assertTrue(parking.add(car2));
        assertTrue(parking.add(truck));
        assertEquals(parking.findBy(v -> true), List.of(car, car2, truck));
    }

    @Test
    public void whenAddThreeCarsAndTruckThenTwoCarsAndTruckAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle car = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        Vehicle truck = new Truck(2);
        assertTrue(parking.add(car));
        assertTrue(parking.add(car2));
        assertFalse(parking.add(car3));
        assertTrue(parking.add(truck));
        assertEquals(parking.findBy(v -> true), List.of(car, car2, truck));
    }

    @Test
    public void whenAddBigTruckThenSmallTruckThenAllAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        assertTrue(parking.add(truck2));
        assertTrue(parking.add(truck));
        assertEquals(parking.findBy(v -> true), List.of(truck, truck2));
    }

    @Test
    public void whenAddSmallTruckAndBigTruckThenSmallTruckAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        assertTrue(parking.add(truck));
        assertFalse(parking.add(truck2));
        assertEquals(parking.findBy(v -> true), List.of(truck));
    }

    @Test
    public void whenAddBigTruckSmallTruckAndCarThenOnlyTrucksAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        Vehicle car = new Car();
        assertTrue(parking.add(truck2));
        assertTrue(parking.add(truck));
        assertFalse(parking.add(car));
        assertEquals(parking.findBy(v -> true), List.of(truck, truck2));
    }

    @Test
    public void whenAddBigTruckCarAndSmallTruckThenBigTruckAndCarAdded() {
        Parking parking = new SimpleParking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        Vehicle car = new Car();
        assertTrue(parking.add(truck2));
        assertTrue(parking.add(car));
        assertFalse(parking.add(truck));
        assertEquals(parking.findBy(v -> true), List.of(car, truck2));
    }

}
