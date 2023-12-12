package pl.put.poznan.transformer.logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A class that extends TransformationDecorator to provide functionality
 * for abbreviating or expanding abbreviations in a string.
 * The abbreviations and their expansions are loaded from a JSON file.
 */
public class AbbreviationTransformer extends TransformationDecorator {
    /**
     * Enum representing the mode of operation - abbreviate or expand.
     */
    public static enum Mode {
        ABBREVIATE,
        EXPAND
    };

    /**
     * Constructor that sets the mode to EXPAND by default.
     *
     * @param tr the StringTransformer to be decorated
     */
    AbbreviationTransformer(StringTransformer tr) {
        super(tr);
        this.mode = Mode.EXPAND;
    }

    /**
     * Constructor that allows setting the mode.
     *
     * @param tr   the StringTransformer to be decorated
     * @param mode the mode of operation - abbreviate or expand
     */
    AbbreviationTransformer(StringTransformer tr, Mode mode) {
        super(tr);
        this.mode = mode;
    }

    /**
     * Transforms the string by abbreviating or expanding it based on the mode.
     *
     * @return the transformed string
     */
    public String transform() {
        return abbreviationDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(AbbreviationTransformer.class);

    private Mode mode;

    /**
     * Decorates the string by abbreviating or expanding it based on the mode.
     *
     * @param s the string to be decorated
     * @return the decorated string
     */
    private String abbreviationDecorate(String s) {
        switch (mode) {
            case ABBREVIATE:
                logger.debug("abbreviate mode");
                for (Map.Entry<String, String> entry : abbrToFullForm.entrySet()) {
                    s = s.replaceAll("\\b(?i)" + Pattern.quote(entry.getValue()) + "\\b", entry.getKey());
                }
                return s;
            case EXPAND:
                logger.debug("expand mode");
                for (Map.Entry<String, String> entry : abbrToFullForm.entrySet()) {
                    s = s.replaceAll("\\b(?i)" + Pattern.quote(entry.getKey()) + "\\b", entry.getValue());
                }
                return s;
            default:
                logger.debug(this.mode.toString() + " mode is not supported");
                return s;
        }
    }

    /*
     * Statically created DualHashBidiMap of abbreviations and their expansions.
     */
    static {
        try {
            InputStream is = AbbreviationTransformer.class.getResourceAsStream("/abbrToExpand.json");
            Map<String, String> map = new ObjectMapper().readValue(is, new TypeReference<Map<String, String>>() {
            });
            abbrToFullForm = new DualHashBidiMap<>(map);
        } catch (IOException e) {
            logger.error("Failed to load abbrToExpand.json");
        }
    }

    private static BidiMap<String, String> abbrToFullForm;
}
