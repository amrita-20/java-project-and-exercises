package com.digitrecognition;

public class NumberRecognizerFactory {
    /**
     * @method NumberRecognizer - created factory method for
     *         returning instances based on passed model type
     * @param modelType
     * @return instance of NumberRecognizer
     */
    public static NumberRecognizer getModel(String modelType){
        switch (modelType.toUpperCase()){
            case "SIMPLE":
                return SimpleNumberRecognizer.getInstance();
            case "COMPLEX":
                return ComplexNumberRecognizer.getInstance();
            default:
                throw new IllegalArgumentException("Invalid model type: " + modelType);
        }
    }
}
