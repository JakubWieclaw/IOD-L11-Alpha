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

/**
 * A class that extends TransformationDecorator to provide functionality
 * for changing LaTeX special symbols (e.g. $, &amp; etc.) into LaTeX format.
 * The symbols are loaded from a JSON file.
 */
public class LatexTransformer extends TransformationDecorator {
    /**
     * Default constructor.
     *
     * @param tr the StringTransformer to be decorated
     */
    LatexTransformer(StringTransformer tr) {
        super(tr);
    }

    /**
     * Transforms the string by changing LaTeX special symbols (e.g. $, &amp; etc.) into LaTeX format.
     *
     * @return the transformed string
     */
    @Override
    public String transform() {
        return latexDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(LatexTransformer.class);

    /**
     * Decorates the string by changing LaTeX special symbols.
     * Treats \\ as a special type of transform which has to be made before every other,
     * because if not the transformer would transform already added \\ to \\backslash.
     *
     * @param s the string to be decorated
     * @return the decorated string
     */
    private String latexDecorate(String s) {
        logger.debug("change to LaTeX mode");
        s = s.replace("\\", "\\backslash");
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
