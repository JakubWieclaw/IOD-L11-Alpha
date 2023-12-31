package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Objects;

public class NoRepeatTransformer extends TransformationDecorator {
    public String transform() {
        return remove_repeat(super.transform());
    }

    NoRepeatTransformer(StringTransformer tr){
        super(tr);
    }

    private String remove_repeat(String s){
        String[] words = s.split(" ");
        ArrayList<String> newList = new ArrayList<String>();
        newList.add(words[0]);
        for (int i = 1; i < words.length; i++){
            if (!Objects.equals(words[i - 1], words[i])){
                newList.add(words[i]);
            }
        }

        return String.join(" ", newList);
    }
}
