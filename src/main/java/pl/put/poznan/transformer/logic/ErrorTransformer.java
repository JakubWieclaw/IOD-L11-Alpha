package pl.put.poznan.transformer.logic;

public class ErrorTransformer implements StringTransformer {
    private final String error;

    public ErrorTransformer(String s){
        error = s;
    }

    @Override
    public String transform() {

        return "Error: unsupported transform " + error;
    }

}
