package com.digitrecognition;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

public class SimpleModelRecognizer implements NumberRecognizer {
    @Override
    public int recognizeNumber() {
            System.out.println("TensorFlow version " + TensorFlow.version());
            SavedModelBundle model = SavedModelBundle.load("D:\\Amrita_NEU\\AED\\digit_recognition_model", "serve");
            Session session = model.session();
            float[] inputData = new float[28 * 28];
            try{
                inputData = ImagePreprocessor.loadImage("image.png");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            Tensor inputTensor = Tensor.create(inputData, Float.class);

            Tensor<Float> outputTensor = session.runner()
                    .feed("serving_default_flatten_input", inputTensor)
                    .fetch("StatefulPartitionedCall")
                    .run()
                    .get(0)
                    .expect(Float.class);

            float[] output = outputTensor.copyTo(new float[1][10])[0];
            int predictedDigit = ImagePreprocessor.argmax(output);
            return predictedDigit;
    }
}
