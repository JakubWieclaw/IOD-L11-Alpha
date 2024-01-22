package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AbbreviationTransformerTest {

    private StringTransformer mockStringTransformer;

    @BeforeEach
    void setUp() {
        mockStringTransformer = Mockito.mock(StringTransformer.class);
    }

    @Test
    void testAbbreviate() {
        // Arrange
        String inputAbbreviate = "profesor Kowalski jest wykładowcą na uczelni.";
        String expectedAbbreviate = "prof. Kowalski jest wykładowcą na uczelni.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(inputAbbreviate);

        AbbreviationTransformer transformerAbbreviate = new AbbreviationTransformer(mockStringTransformer, AbbreviationTransformer.Mode.ABBREVIATE);

        // Act
        String resultAbbreviate = transformerAbbreviate.transform();

        // Assert
        assertEquals(expectedAbbreviate, resultAbbreviate);
    }

    @Test
    void testExpand() {
        // Arrange
        String inputExpand = "dr  Jan specjalizuje się w biologii.";
        String expectedExpand = "doktor Jan specjalizuje się w biologii.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(inputExpand);

        AbbreviationTransformer transformerExpand = new AbbreviationTransformer(mockStringTransformer, AbbreviationTransformer.Mode.EXPAND);

        // Act
        String resultExpand = transformerExpand.transform();

        // Assert
        assertEquals(expectedExpand, resultExpand);
    }

    @Test
    void testUndefinedAbbreviation() {
        // Arrange
        String inputUndefinedAbbreviation = "Some text with an undefined abbreviation like XYZ.";
        Mockito.when(mockStringTransformer.transform()).thenReturn(inputUndefinedAbbreviation);

        AbbreviationTransformer transformerUndefinedAbbreviation = new AbbreviationTransformer(mockStringTransformer, AbbreviationTransformer.Mode.ABBREVIATE);

        // Act
        String resultUndefinedAbbreviation = transformerUndefinedAbbreviation.transform();

        // Assert
        assertEquals(inputUndefinedAbbreviation, resultUndefinedAbbreviation);
    }
}
