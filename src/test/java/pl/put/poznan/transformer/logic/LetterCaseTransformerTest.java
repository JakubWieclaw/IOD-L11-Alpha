package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterCaseTransformerTest {

    private StringTransformer mockStringTransformer;

    @BeforeEach
    void setUp() {
        mockStringTransformer = Mockito.mock(StringTransformer.class);
    }

    @Test
    void testTransformToUpperCase() {
        // Arrange
        String input = "This is a test string.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LetterCaseTransformer letterCaseTransformer = new LetterCaseTransformer(mockStringTransformer, LetterCaseTransformer.Mode.UPPER);

        // Act
        String result = letterCaseTransformer.transform();

        // Assert
        assertEquals("THIS IS A TEST STRING.", result);
    }

    @Test
    void testTransformToLowerCase() {
        // Arrange
        String input = "This Is A Test String.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LetterCaseTransformer letterCaseTransformer = new LetterCaseTransformer(mockStringTransformer, LetterCaseTransformer.Mode.LOWER);

        // Act
        String result = letterCaseTransformer.transform();

        // Assert
        assertEquals("this is a test string.", result);
    }

    @Test
    void testTransformToCapitalize() {
        // Arrange
        String input = "this is a test string.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LetterCaseTransformer letterCaseTransformer = new LetterCaseTransformer(mockStringTransformer, LetterCaseTransformer.Mode.CAPITALIZE);

        // Act
        String result = letterCaseTransformer.transform();

        // Assert
        assertEquals("This Is A Test String.", result);
    }

    @Test
    void testTransformToCapitalizeAllUppercase() {
        // Arrange
        String input = "THIS IS A TEST STRING.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LetterCaseTransformer letterCaseTransformer = new LetterCaseTransformer(mockStringTransformer, LetterCaseTransformer.Mode.CAPITALIZE);

        // Act
        String result = letterCaseTransformer.transform();

        // Assert
        assertEquals("This Is A Test String.", result);
    }

    // Test with special characters
    @Test
    void testTransformToCapitalizeSpecialCharacters() {
        // Arrange
        String input = "this is a test string with special characters: !@#$%^&*()_+";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LetterCaseTransformer letterCaseTransformer = new LetterCaseTransformer(mockStringTransformer, LetterCaseTransformer.Mode.CAPITALIZE);

        // Act
        String result = letterCaseTransformer.transform();

        // Assert
        assertEquals("This Is A Test String With Special Characters: !@#$%^&*()_+", result);
    }
}
