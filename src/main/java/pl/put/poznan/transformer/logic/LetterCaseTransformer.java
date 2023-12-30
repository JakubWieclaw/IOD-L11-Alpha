package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that extends TransformationDecorator to provide functionality
 * for changing letter case in words.
 * Three possible modes exist: UPPER - transform all letters to uppercase,
 * LOWER - transform all letters to lowercase and CAPITALIZE - capitalize
 * first letter of each word.
 */
public class LetterCaseTransformer extends TransformationDecorator {
    /**
     * Enum representing the mode of operation - UPPER/LOWER/CAPITALIZE.
     */
    public static enum Mode {
        UPPER,
        LOWER,
        CAPITALIZE
    };

    /**
     * Constructor.
     *
     * @param tr the StringTransformer to be decorated
     * @param mode the transformation mode (UPPER, LOWER, CAPITALIZE)
     */
    LetterCaseTransformer(StringTransformer tr, Mode mode) {
        super(tr);
        this.mode = mode;
    }

    /**
     * Transforms the string by changing its letter-case.
     *
     * @return the transformed string
     */
    public String transform() {
        return letterSizeDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(LetterCaseTransformer.class);

    private Mode mode;

    /**
     * Decorates the string by changing the letter-case based on the mode.
     * For UPPER/LOWER uses toUpper/LowerCase() on whole string.
     * For CAPITALIZE splits string into words (split on spaces)
     * and capitalizes first letter of each word.
     *
     * @param s the string to be decorated
     * @return the decorated string
     */
    private String letterSizeDecorate(String s) {
        switch (mode) {
            case UPPER:
                logger.debug("upper mode");
                return s.toUpperCase();
            case LOWER:
                logger.debug("lower mode");
                return s.toLowerCase();
            case CAPITALIZE:
                logger.debug("capitalize mode");
                String[] words = s.split(" ");
                List<String> transformedWords = new ArrayList<>();
                for (String word : words) {
                    transformedWords.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
                }
                String result = String.join(" ", transformedWords);
                return result;
            default:
                logger.debug(this.mode.toString() + " mode is not supported");
                return s;
        }
    }
}
