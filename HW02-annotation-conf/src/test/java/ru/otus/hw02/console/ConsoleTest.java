package ru.otus.hw02.console;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing Console.class")
public class ConsoleTest {

    @DisplayName("Checking that class Console is correctly created")
    @Test
    void shouldCorrectCreated() {
        Console console = new ConsoleImpl();
        assertNotNull(console);
    }

    @DisplayName("Testing correct printing")
    @Test
    void shouldCorrectPrint() {
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
//        System.

    }
}
