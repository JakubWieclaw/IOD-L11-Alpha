package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.StringTransformer;

public class IdentityTransformer implements StringTransformer {
    private final String str;

    public IdentityTransformer(String s){
        str = s;
    }

    @Override
    public String transform() {
        return str;
    }
}
