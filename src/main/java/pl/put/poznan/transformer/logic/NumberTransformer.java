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
 * for changing numbers in numerical form to a word form.
 * Numerals and their word counterparts are loaded from a JSON file.
 */
public class NumberTransformer extends TransformationDecorator {
    /**
     * Default constructor.
     *
     * @param tr the StringTransformer to be decorated
     */
    NumberTransformer(StringTransformer tr) {
        super(tr);
    }

    /**
     * Transforms the string by changing numbers in their numerical form to their
     * word forms (e.g. 1--one).
     *
     * @return the transformed string
     */
    @Override
    public String transform() {
        return numberDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(NumberTransformer.class);

    /**
     * Decorates the string by changing numbers in their numerical form to their
     * word forms (e.g. 1--one).
     *
     * @param s the string to be decorated
     * @return the decorated string
     */
    private String numberDecorate(String s) {
        logger.debug("number expand mode");
        for (Map.Entry<String, String> entry : numberToText.entrySet()) {
            s = s.replaceAll("\\b(?i)" + Pattern.quote(entry.getKey()) + "\\b", entry.getValue());
        }

        return s;
    }

    static {
        try {
            InputStream is = AbbreviationTransformer.class.getResourceAsStream("/numberToText.json");
            numberToText = new ObjectMapper().readValue(is, new TypeReference<HashMap<String, String>>() {
            });
        } catch (IOException e) {
            logger.error("Failed to load numberToText.json");
        }
    }

    private static HashMap<String, String> numberToText;
}
