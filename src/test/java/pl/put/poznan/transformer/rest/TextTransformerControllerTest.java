package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TextTransformerControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        TextTransformerController controller = new TextTransformerController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGet() throws Exception {
        // Arrange
        String text = "test";
        String[] transforms = {"UPPER"};

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/{text}", text)
                        .param("transforms", transforms)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"transformedText\":\"TEST\"}"));
    }

    @Test
    void testPost() throws Exception {
        // Arrange
        String text = "test";
        String[] transforms = {"UPPER"};

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/{text}", text)
                        .param("transforms", transforms)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"transformedText\":\"TEST\"}"));
    }
}
