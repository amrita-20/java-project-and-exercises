package com.digitrecognition;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

public class ComplexModelRecognizer implements NumberRecognizer{
    @Override
    public int recognizeNumber() {
        System.out.println("TensorFlow version " + TensorFlow.version());
        SavedModelBundle model = SavedModelBundle.load("D:\\Amrita_NEU\\AED\\complex_number_recognition_model", "serve");
        Session session = model.session();
        int batchSize = 1;
        int height = 28;
        int width = 28;
        int channels = 1;
        float[][][][] inputData = new float[batchSize][height][width][channels];
        try{
            inputData = ImagePreprocessor.preprocessImage("image.png");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Tensor inputTensor = Tensor.create(inputData, Float.class);

        Tensor<Float> outputTensor = session.runner()
                .feed("serving_default_conv2d_input", inputTensor)
                .fetch("StatefulPartitionedCall")
                .run()
                .get(0)
                .expect(Float.class);

        float[] output = outputTensor.copyTo(new float[1][10])[0];
        int predictedDigit = ImagePreprocessor.argmax(output);
        return predictedDigit;
    }
}
