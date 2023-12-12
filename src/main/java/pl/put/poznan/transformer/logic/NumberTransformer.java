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

public class NumberTransformer extends TransformationDecorator {
    NumberTransformer(StringTransformer tr) {
        super(tr);
    }

    @Override
    public String transform() {
        return numberDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(NumberTransformer.class);

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
