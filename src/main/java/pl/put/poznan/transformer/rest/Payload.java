package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class Payload {
    public Payload(String transformedText) {
        this.transformedText = transformedText;
    }

    public String getTransformedText() {
        return transformedText;
    }

    public void setTransformedText(String transformedText) {
        this.transformedText = transformedText;
    }

    @JsonRawValue private String transformedText;

}
