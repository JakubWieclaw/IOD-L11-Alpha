package pl.put.poznan.transformer.logic;

/**
 * This class builds a full string transformer by stacking decorators (StringTransformer)
 * specified in String[] transforms.
 */
public class DecoratorBuilder {

    /**
     * This method goes through all the transforms in transforms.
     * Every iteration it looks for transform name. When it finds a transform
     * name like UPPER, LOWER, CAPITALIZE, ABBREVIATE, EXPAND, INVERSE, NUM_EXPAND
     * this method stacks previous transformer into new one of the fitting type
     * (e.g. if it finds UPPER it stacks prev. transformer into new LetterCaseTransformer)
     * If transform name is not known it uses new ErrorTransformer
     *
     * @param baseTransformer base StringTransformer to be specified
     * @param transforms list of commands corresponding to different transformers
     * @return a built StringTransformer
     */
    public static StringTransformer composeTransformer(StringTransformer baseTransformer, String[] transforms){
        // HOW to handle unknown transformer
        // for now we ignore the value
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
                    base = new NumberTransformer(base);
                    break;
                default:
                    return new ErrorTransformer(transform);
            }
        }
        return base;
    }

}
