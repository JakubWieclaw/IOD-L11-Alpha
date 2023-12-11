package pl.put.poznan.transformer.logic;

public class DecoratorBuilder {

    // HOW to handle unknown transformer
    // for now we ignore the value
    public static StringTransformer composeTransformer(StringTransformer baseTransformer, String[] transforms){
        StringTransformer base = baseTransformer;
        for (String transform : transforms){
            switch (transform){
                case "UPPER" :
                    base = new LetterCaseTransformer(base, LetterCaseTransformer.Mode.UPPER);
                    break;
                case "LOWER" :
                    base = new LetterCaseTransformer(base, LetterCaseTransformer.Mode.LOWER);
                    break;
                case "CAPITALIZE" :
                    base = new LetterCaseTransformer(base, LetterCaseTransformer.Mode.CAPITALIZE);
                    break;
                case "ABBREVIATE" :
                    base = new AbbreviationTransformer(base, AbbreviationTransformer.Mode.ABBREVIATE);
                    break;
                case "EXPAND" :
                    base = new AbbreviationTransformer(base, AbbreviationTransformer.Mode.EXPAND);
                    break;
                case "INVERSE" :
                    base = new InversionTransformer(base);
                    break;
                case "NUM_EXPAND" :
                    base = new NumberTransformer(base, NumberTransformer.Mode.EXPAND);
                    break;
                case "NUM_ABBREVIATE" :
                    base = new NumberTransformer(base, NumberTransformer.Mode.ABBREVIATE);
                    break;
                default:
                    return new ErrorTransformer(transform);
            }
        }
        return base;
    }

}
