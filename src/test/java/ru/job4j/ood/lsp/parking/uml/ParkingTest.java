package ru.job4j.ood.lsp.parking.uml;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class ParkingTest {

    @Test
    public void whenTruckSizeLessTwoThenException() {
        assertThrows(IllegalArgumentException.class, () -> {
                Vehicle truck = new Truck(1);
        });
    }

    @Test
    public void whenAddTwoCarsAndTruckThenAllAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle car = new Car();
        Vehicle car2 = new Car();
        Vehicle truck = new Truck(2);
        assertTrue(parking.add(car));
        assertTrue(parking.add(car2));
        assertTrue(parking.add(truck));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(car, car2, truck));
    }

    @Test
    public void whenAddThreeCarsAndTruckThenTwoCarsAndTruckAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle car = new Car();
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        Vehicle truck = new Truck(2);
        assertTrue(parking.add(car));
        assertTrue(parking.add(car2));
        assertFalse(parking.add(car3));
        assertTrue(parking.add(truck));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(car, car2, truck));
    }

    @Test
    public void whenAddBigTruckThenSmallTruckThenAllAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        assertTrue(parking.add(truck));
        assertTrue(parking.add(truck));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(truck2, truck));
    }

    @Test
    public void whenAddSmallTruckAndBigTruckThenSmallTruckAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        assertTrue(parking.add(truck));
        assertFalse(parking.add(truck2));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(truck));
    }

    @Test
    public void whenAddBigTruckSmallTruckAndCarThenOnlyTrucksAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        Vehicle car = new Car();
        assertTrue(parking.add(truck2));
        assertTrue(parking.add(truck));
        assertFalse(parking.add(car));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(truck2, truck));
    }

    @Test
    public void whenAddBigTruckCarAndSmallTruckThenBigTruckAndCarAdded() {
        ParkingInterface parking = new Parking(2, 1);
        Vehicle truck = new Truck(2);
        Vehicle truck2 = new Truck(3);
        Vehicle car = new Car();
        assertTrue(parking.add(truck2));
        assertTrue(parking.add(car));
        assertFalse(parking.add(truck));
        assertThat(parking.findBy(v -> true)).isEqualTo(List.of(truck2, car));
    }

}