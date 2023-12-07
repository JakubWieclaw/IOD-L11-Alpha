package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LetterCaseTransformer extends TransformationDecorator {
    public static enum Mode {
        UPPER,
        LOWER,
        CAPITALIZE
    };

    LetterCaseTransformer(StringTransformer tr, Mode mode) {
        super(tr);
        this.mode = mode;
    }

    public String transform() {
        return letterSizeDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(LetterCaseTransformer.class);

    private Mode mode;

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
                String result = "";
                for (String word : words) {
                    result += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
                }
                return result;
            default:
                logger.debug(this.mode.toString() + " mode is not supported");
                return s;
        }
    }
}
