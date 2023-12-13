package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class that extends TransformationDecorator to provide functionality
 * for inverting a string while also preserving letter case at every index.
 */
public class InversionTransformer extends TransformationDecorator {

    InversionTransformer(StringTransformer tr) {
        super(tr);
    }

    /**
     * Transforms the string by inverting it.
     *
     * @return the transformed string
     */
    public String transform() {
        return inversionDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(InversionTransformer.class);

    /**
     * Decorates the string by inverting a string and changing the letter case
     * of the letter at every index i in inverted string to be the same as letter case
     * of the letter at that index in original string.
     *
     * @param s the string to be decorated
     * @return the decorated string
     */
    private String inversionDecorate(String s) {
        logger.debug("inversion mode");
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char reverse_c = s.charAt(s.length()-1-i);
            if (Character.isUpperCase(c)) {
                reverse_c = Character.toUpperCase(reverse_c);
            } else if (Character.isLowerCase(c)) {
                reverse_c = Character.toLowerCase(reverse_c);
            }
            result += reverse_c;

        }
        return result;
    }
}
