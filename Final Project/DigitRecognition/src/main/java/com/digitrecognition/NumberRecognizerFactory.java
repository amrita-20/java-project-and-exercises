package com.digitrecognition;

public class NumberRecognizerFactory {
    public static NumberRecognizer getModel(String modelType){
        switch (modelType.toUpperCase()){
            case "SIMPLE":
                return new SimpleModelRecognizer();
            case "COMPLEX":
                return new ComplexModelRecognizer();
            default:
                throw new IllegalArgumentException("Invalid model type: " + modelType);
        }
    }
}
