package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InversionTransformerTest {


    private StringTransformer mockTransformer;
    @BeforeEach
    public void setUp() {
        mockTransformer = Mockito.mock(StringTransformer.class);
    }
    @Test
    public void testTransform() {
        Mockito.when(mockTransformer.transform()).thenReturn("Hello World");
        InversionTransformer transformer = new InversionTransformer(mockTransformer);
        String result = transformer.transform();
        assertEquals("Dlrow Olleh", result);
    }

    @Test
    public void testempty() {
        Mockito.when(mockTransformer.transform()).thenReturn("");
        InversionTransformer transformer = new InversionTransformer(mockTransformer);
        String result = transformer.transform();
        assertEquals("", result);
    }

    // Test with special characters
    @Test
    public void testSpecialCharacters() {
        Mockito.when(mockTransformer.transform()).thenReturn("Hello World @#$%^&*()_+|}{:?><");
        InversionTransformer transformer = new InversionTransformer(mockTransformer);
        String result = transformer.transform();
        assertEquals("<>?:{}|+_)(*&^%$#@ dlroW olleH", result);
    }
}
