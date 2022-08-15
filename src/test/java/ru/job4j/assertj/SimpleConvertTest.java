package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "second", "three");
        assertThat(list).hasSize(7)
                .contains("second", "four")
                .contains("three", Index.atIndex(2))
                .containsAnyOf("four", "zero", "ten")
                .containsOnly("first", "second", "three", "four", "five")
                .doesNotContain("ten", "twenty")
                .startsWith("first", "second")
                .endsWith("five", "second", "three")
                .containsSequence("four", "five", "second")
                .doesNotContainSequence("first", "second", "third");
        assertThat(list).first()
                .isEqualTo("first")
                .isNotNull();
        assertThat(list).element(2)
                .isNotNull()
                .isEqualTo("three");

    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "second", "three");
        assertThat(set).hasSize(5)
                .contains("second", "four")
                .containsAnyOf("four", "zero", "ten")
                .containsOnly("first", "second", "three", "four", "five")
                .doesNotContain("ten", "twenty")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second", "three", "four", "five")
                .containsValues(0, 1, 2, 3, 4)
                .doesNotContainKey("six")
                .doesNotContainValue(5)
                .containsEntry("first", 0)
                .containsEntry("four", 3);
    }

}