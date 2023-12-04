package pl.put.poznan.transformer.logic;

public abstract class TransformationDecorator implements StringTransformer {
    private final StringTransformer transformer;

    TransformationDecorator(StringTransformer tr){
        transformer = tr;
    }

    @Override
    public String transform() {
        return transformer.transform();
    }
}
