// Source code is decompiled from a .class file using FernFlower decompiler.
package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoRepeatTransformerTest {

    private StringTransformer mockTransformer;

    @BeforeEach
    public void setUp() {
        mockTransformer = Mockito.mock(StringTransformer.class);
    }

    @Test
    public void testTransform_NoRepeats() {
        // Arrange
        Mockito.when(mockTransformer.transform()).thenReturn("hello hello world");

        // Act
        NoRepeatTransformer transformer = new NoRepeatTransformer(mockTransformer);
        String result = transformer.transform();

        // Assert
        assertEquals("hello world", result, "Transforming string with repeated words should remove duplicates");
    }

    @Test
    public void testTransform_EmptyInput() {
        // Arrange
        Mockito.when(mockTransformer.transform()).thenReturn("");

        // Act
        NoRepeatTransformer transformer = new NoRepeatTransformer(mockTransformer);
        String result = transformer.transform();

        // Assert
        assertEquals("", result, "Transforming an empty string should return an empty string");
    }

    // Test where all words are repeated
    @Test
    public void testTransform_AllRepeats() {
        // Arrange
        Mockito.when(mockTransformer.transform()).thenReturn("hello hello hello hello world world world world");

        // Act
        NoRepeatTransformer transformer = new NoRepeatTransformer(mockTransformer);
        String result = transformer.transform();

        // Assert
        assertEquals("hello world", result, "Transforming string with repeated words should remove duplicates");
    }
}
