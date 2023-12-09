package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InversionTransformer extends TransformationDecorator {

    InversionTransformer(StringTransformer tr) {
        super(tr);
    }

    public String transform() {
        return inversionDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(InversionTransformer.class);


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
