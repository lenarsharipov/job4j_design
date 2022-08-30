package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> filter = e -> e % 2 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(4).containsSequence(1, 3, 5, 7);
    }

    @Test
    void whenRemoveIf2() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> filter = e -> e % 3 != 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(2).containsSequence(3, 6);
    }

    @Test
    void whenRemoveIfNoChange() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> filter = e -> e % 10 == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(7).containsSequence(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenReplaceIf() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int value = 777;
        Predicate<Integer> filter = e -> e % 2 == 0;
        ListUtils.replaceIf(input, filter, value);
        assertThat(input).hasSize(7).containsSequence(1, 777, 3, 777, 5, 777, 7);
    }

    @Test
    void whenReplaceIfNoChange() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int value = 777;
        Predicate<Integer> filter = e -> e % 10 == 0;
        ListUtils.replaceIf(input, filter, value);
        assertThat(input).hasSize(7).containsSequence(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenRemoveAll() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(4).containsSequence(4, 5, 6, 7);
    }

    @Test
    void whenRemoveAll2() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(0).containsSequence();
    }

    @Test
    void whenRemoveAllNoChange() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        List<Integer> elements = new ArrayList<>(Arrays.asList(13, 18, 77));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(7).containsSequence(1, 2, 3, 4, 5, 6, 7);
    }

}