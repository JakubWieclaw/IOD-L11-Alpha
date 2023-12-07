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

public class AbbreviationTransformer extends TransformationDecorator {
    public static enum Mode {
        ABBREVIATE,
        EXPAND
    };

    AbbreviationTransformer(StringTransformer tr, Mode mode = Mode.EXPAND) {
        super(tr);
        this.mode = mode;
    }

    public String transform() {
        return abbreviationDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(AbbreviationTransformer.class);

    private Mode mode;

    private String abbreviationDecorate(String s) {
        switch (mode) {
            case ABBREVIATE:
                logger.debug("abbreviate mode");
                for (Map.Entry<String, String> entry : abbrToFullForm.entrySet()) {
                    s = s.replaceAll("(?i)" + Pattern.quote(entry.getValue()), entry.getKey());
                }
                return s;
            case EXPAND:
                logger.debug("expand mode");
                for (Map.Entry<String, String> entry : abbrToFullForm.entrySet()) {
                    s = s.replaceAll("(?i)" + Pattern.quote(entry.getKey()), entry.getValue());
                }
                return s;
            default:
                logger.debug(this.mode.toString() + " mode is not supported");
                return s;
        }
    }

    static {
        try {
            InputStream is = AbbreviationTransformer.class.getResourceAsStream("/abbrToExpand.json");
            Map<String, String> map = new ObjectMapper().readValue(is, new TypeReference<Map<String, String>>(){});
            abbrToFullForm = new DualHashBidiMap<>(map);
        } catch (IOException e) {
            logger.error("Failed to load abbrToExpand.json");
        }
    }

    private static BidiMap<String, String> abbrToFullForm;
}
