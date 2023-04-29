package com.digitrecognition;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

public class ComplexNumberRecognizer implements NumberRecognizer{
    private static ComplexNumberRecognizer instance;
    private static SavedModelBundle model;
    private static String MODEL_PATH = "DigitRecognition\\src\\main\\resources\\complex_number_recognition_model";

    //making the constructor private, the class cannot be instantiated from outside the class
    private ComplexNumberRecognizer(){
        model = SavedModelBundle.load(MODEL_PATH, "serve");
    };

    /**
     * @method recognizeNumber - for predicting number using CNN model
     * @return predicted digit
     */
    @Override
    public int recognizeNumber(String imagePath) {
        model = SavedModelBundle.load(MODEL_PATH, "serve");
        Session session = model.session();
        float [][] resizedArr = ImagePreProcessorUtil.processImage(imagePath,Constants.PIXEL_ROW, Constants.PIXEL_COL);
        float[][][][] inputData = ImagePreProcessorUtil.convert2DTo4DArray(resizedArr);

        Tensor inputTensor = Tensor.create(inputData, Float.class);

        Tensor<Float> outputTensor = session.runner()
                .feed("serving_default_conv2d_input", inputTensor)
                .fetch("StatefulPartitionedCall")
                .run()
                .get(0)
                .expect(Float.class);

        float[] output = outputTensor.copyTo(new float[1][10])[0];
        int predictedDigit = ImagePreProcessorUtil.argmax(output);
        return predictedDigit;
    }
    /**
     This method ensures that only one instance of ComplexNumberRecognizer is created and returned.
     @return an instance of ComplexNumberRecognizer
     */
    public static ComplexNumberRecognizer getInstance() {
        if (instance == null) {
            // synchronized block to ensure thread-safety
            synchronized (ComplexNumberRecognizer.class) {
                // Check again if instance is null to avoid race conditions
                if (instance == null) {
                    instance = new ComplexNumberRecognizer();
                }
            }
        }
        return instance;
    }
}
