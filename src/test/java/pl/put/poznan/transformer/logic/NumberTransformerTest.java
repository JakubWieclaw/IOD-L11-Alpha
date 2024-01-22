package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTransformerTest {

    private StringTransformer mockStringTransformer;

    @BeforeEach
    void setUp() {
        mockStringTransformer = Mockito.mock(StringTransformer.class);
    }

    @Test
    void testTransform() {
        // Arrange
        // We create a string with numbers that need to be transformed to their word forms
        String input = "2 zlote i 1 zloty";
        // We define what the mock StringTransformer should return when its transform method is called
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        // We create a new NumberTransformer with the mock StringTransformer
        NumberTransformer numberTransformer = new NumberTransformer(mockStringTransformer);

        // Act
        // We call the transform method of the NumberTransformer
        String result = numberTransformer.transform();

        // Assert
        // We check that the result is as expected
        String expected = "dwa zlote i jeden zloty";
        assertEquals(expected, result);
    }

    @Test
    void testAll(){
        String input = "1 2 3 4 5 6 7 8 9 10 100 200 500";
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);
        NumberTransformer numberTransformer = new NumberTransformer(mockStringTransformer);
        String result = numberTransformer.transform();
        String expected = "jeden dwa trzy cztery pięć sześć siedem osiem dziewięć dziesięć sto dwieście pięćset";
        assertEquals(expected, result);
    }
}
