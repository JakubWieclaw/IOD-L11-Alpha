package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LatexTransformerTest {

    private StringTransformer mockStringTransformer;

    @BeforeEach
    void setUp() {
        mockStringTransformer = Mockito.mock(StringTransformer.class);
    }

    @Test
    void testTransform() {
        // Arrange
        // We create a string with special LaTeX symbols that need to be transformed
        String input = "This is a string with special LaTeX symbols like $, &, etc.";
        // We define what the mock StringTransformer should return when its transform method is called
        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        // We create a new LatexTransformer with the mock StringTransformer
        LatexTransformer latexTransformer = new LatexTransformer(mockStringTransformer);

        // Act
        // We call the transform method of the LatexTransformer
        String result = latexTransformer.transform();

        // Assert
        // We check that the result is as expected
        String expected = "This is a string with special LaTeX symbols like \\$, \\&, etc.";
        assertEquals(expected, result);
    }

    @Test
    void testSpecialCharacters(){
        String input = "This is a string with special LaTeX symbols like ^, _, {, }, ~";

        Mockito.when(mockStringTransformer.transform()).thenReturn(input);

        LatexTransformer latexTransformer = new LatexTransformer(mockStringTransformer);

        String result = latexTransformer.transform();

        String expected = "This is a string with special LaTeX symbols like \\textasciicircum, \\_, \\{, \\}, \\textasciitilde";
        assertEquals(expected, result);
    }
}
