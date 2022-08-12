package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(4, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank()
                .isNotNull();
    }

    @Test
    void verticesOfCube() {
        Box box = new Box(6, 8);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(6)
                .isEven()
                .isNotZero()
                .isPositive();
    }

    @Test
    void verticesOfUnknown() {
        Box box = new Box(3, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(-1)
                .isNegative()
                .isNotZero();
    }

    @Test
    void whenExists() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isTrue()
                .isNotNull();
    }

    @Test
    void whenNotExists() {
        Box box = new Box(3, 10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse()
                .isNotNull();
    }

    @Test
    void sphereArea() {
        Box box = new Box(0, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(113.098d, withPrecision(0.006d))
                .isCloseTo(113.097d, withPrecision(0.01d))
                .isCloseTo(113.097d, Percentage.withPercentage(1.0d))
                .isGreaterThan(113.097d)
                .isLessThan(113.098d);
    }

    @Test
    void tetrahedronArea() {
        Box box = new Box(4, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(62.353d, withPrecision(0.006d))
                .isCloseTo(62.352d, withPrecision(0.01d))
                .isCloseTo(62.352d, Percentage.withPercentage(1.0d))
                .isGreaterThan(62.352d)
                .isLessThan(62.354d);
    }
}