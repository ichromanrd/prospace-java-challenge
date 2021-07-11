package com.ichromanrd.prospace;

import com.ichromanrd.prospace.exception.InvalidFormatException;
import com.ichromanrd.prospace.exception.SystemErrorException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanGalacticConverterTest {
    private final RomanGalacticConverter converter = RomanGalacticConverter.instance();
    private final String expectedInvalidFormatMessage = "Requested number is in invalid format";
    private final String expectedSystemErrorMessage = "I have no idea what you are talking about";

    @Test
    public void testSystemErrorException() {
        List<String> input = Arrays.asList("wood", "could", "a", "woodchuck", "chuck");
        Exception exception = assertThrows(SystemErrorException.class, () -> {
           converter.convert(input);
        });

        assertNotNull(exception);
        assertEquals(expectedSystemErrorMessage, exception.getMessage());
    }

    @Test
    public void testSystemErrorException2() {
        List<String> input = Arrays.asList("glob", "abc");
        Exception exception = assertThrows(SystemErrorException.class, () -> {
            converter.convert(input);
        });

        assertNotNull(exception);
        assertEquals(expectedSystemErrorMessage, exception.getMessage());
    }

    @Test
    public void testInvalidFormatException() {
        List<String> input = Arrays.asList("glob", "glob", "glob", "glob");
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            converter.convert(input);
        });

        assertNotNull(exception);
        assertEquals(expectedInvalidFormatMessage, exception.getMessage());
    }

    @Test
    public void testInvalidFormatException2() {
        List<String> input = Arrays.asList("pish", "pish", "pish", "pish");
        Exception exception = assertThrows(InvalidFormatException.class, () -> {
            converter.convert(input);
        });
        assertNotNull(exception);
        assertEquals(expectedInvalidFormatMessage, exception.getMessage());
    }

    @Test
    public void testValidFormat() {
        List<String> input = Arrays.asList("pish", "pish", "pish", "glob", "pish");
        assertDoesNotThrow(() -> {
            converter.convert(input);
        });
    }

    @Test
    public void testCalculationResult() {
        List<String> input = Arrays.asList("pish", "pish", "pish", "glob", "pish");
        int r1 = converter.convert(input);
        int expectedR1 = 39;
        assertEquals(expectedR1, r1);

        input = Arrays.asList("omni", "ugwo", "omni", "glob", "glob", "glob");
        int r2 = converter.convert(input);
        int expectedR2 = 1903;
        assertEquals(expectedR2, r2);
    }
}
