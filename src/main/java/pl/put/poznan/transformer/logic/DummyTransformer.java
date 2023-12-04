package pl.put.poznan.transformer.logic;

public class DummyTransformer extends TransformationDecorator {

    DummyTransformer(StringTransformer tr) {
        super(tr);
    }

    public String transform() {
        return dummyDecorate(super.transform());
    }

    private String dummyDecorate(String s) {
        return s+s;
    }
}
