package pl.put.poznan.transformer.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberTransformer extends TransformationDecorator {
    public static enum Mode {
        ABBREVIATE,
        EXPAND
    };

    NumberTransformer(StringTransformer tr) {
        super(tr);
        this.mode = Mode.EXPAND;
    }

    NumberTransformer(StringTransformer tr, Mode mode) {
        super(tr);
        this.mode = mode;
    }

    @Override
    public String transform() {
        return numberDecorate(super.transform());
    }

    private static final Logger logger = LoggerFactory.getLogger(NumberTransformer.class);

    private Mode mode;

    // dummy functionality (class needed for UML graph)
    private String numberDecorate(String s) {
        return s;
    }
}
