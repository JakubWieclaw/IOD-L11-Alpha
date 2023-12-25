package pl.put.poznan.transformer.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LatexTransformer extends TransformationDecorator {
    LatexTransformer(StringTransformer tr) {
        super(tr);
    }

    @Override
    public String transform() {
        return latexDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(LatexTransformer.class);

    private String latexDecorate(String s) {
        logger.debug("change to LaTeX mode");
        for (Map.Entry<String, String> entry : textToLatex.entrySet()) {
            logger.debug(entry.getValue());
            s = s.replace(entry.getKey(), entry.getValue());
            logger.debug(s);
        }

        return s;
    }

    static {
        try {
            InputStream is = AbbreviationTransformer.class.getResourceAsStream("/textToLatex.json");
            textToLatex = new ObjectMapper().readValue(is, new TypeReference<HashMap<String, String>>() {
            });
        } catch (IOException e) {
            logger.error("Failed to load textToLatex.json");
        }
    }

    private static HashMap<String, String> textToLatex;
}
