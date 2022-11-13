package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;



@Disabled
class GeneratorTest {

    @Test
    public void whenPassValidArgsThenGetValidString() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a Petr Arsentev, Who are you";
        Generator generator = new GeneratorReport();
        String rsl = generator.produce(template, args);
        assertThat(expected).isEqualTo(rsl);
    }

    @Test
    public void whenPassInvalidTemplateThenGetException() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String template = "I am a ${name}, My phone is ${phone}?";
        Generator generator = new GeneratorReport();
        assertThrows(IllegalArgumentException.class, () -> {
           generator.produce(template, args);
        });
    }

    @Test
    public void whenPassExtraKeyToMapThenGetException() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("phone", "+7 843 222 22 22");
        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new GeneratorReport();
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, args);
        });
    }

}