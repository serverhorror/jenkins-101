package dev.assetkit.java101;

import dev.assetkit.java101.Greeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void greet() {
        Greeter g = new Greeter("Alex");
        assertEquals("Alex", g.greet());
    }
}