package com.digitrecognition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class NumberDrawGUI extends Application {

    private Canvas drawingCanvas;
    private GraphicsContext gc;
    private boolean isDrawing = false;
    Text text1 = new Text("Predicted No : ");
   // Label label =new Label("Predicted No:");

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Drawing GUI");

        drawingCanvas = new Canvas(150, 150);
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.setLineCap(StrokeLineCap.ROUND);

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            System.out.println(e.getSceneX());
            isDrawing = true;
        });

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            if (isDrawing) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            }
        });

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            isDrawing = false;
        });
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(event -> {
            gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
            text1.setText("");
        });

        Button saveButton = new Button("Predict");
        saveButton.setOnAction(event -> {
            WritableImage image = drawingCanvas.snapshot(new SnapshotParameters(), null);
            File file = new File("image.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                getModel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //WritableImage image = drawingCanvas.snapshot(new SnapshotParameters(), null);
           // PixelReader pixelReader = (PixelReader) image.getPixelWriter();
        });

        GridPane root = new GridPane();
        Pane pane = new Pane();
        pane.getChildren().add(drawingCanvas);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(60);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(40);
        root.getColumnConstraints().addAll(column1, column2);
        root.setVgap(20);
        root.setHgap(20);
        root.addRow(0, pane, text1);
        root.addRow(1, clearButton);
        root.addRow(1, saveButton);

        //StackPane root = new StackPane(drawingCanvas, saveButton);
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getModel() throws Exception{
        System.out.println("TensorFlow version " + TensorFlow.version());
        SavedModelBundle model = SavedModelBundle.load("D:\\Amrita_NEU\\AED\\digit_recognition_model", "serve");
        Session session = model.session();

//        float[][] inputData = {{2.5f, 67.0f}, {87}}; // Your input data
         float[] inputData = ImageUtils.loadImage("image.png");
        /*BufferedImage image = ImageIO.read(new File("image.png"));

        // Resize the image
        int width = 28;
        int height = 28;
        BufferedImage resizedImage = ImagePreprocessor.resize(image, width, height);

        // Convert the image to grayscale
        BufferedImage grayscaleImage = ImagePreprocessor.toGrayscale(resizedImage);

        // Flatten the image
        float[] flattenedImage = ImagePreprocessor.flatten(grayscaleImage);
*/

        Tensor inputTensor = Tensor.create(inputData, Float.class);

        Tensor<Float> outputTensor = session.runner()
                .feed("serving_default_flatten_input", inputTensor)
                .fetch("StatefulPartitionedCall")
                .run()
                .get(0)
                .expect(Float.class);

//        float[][] outputData = new float[][]; // Shape of output tensor
//        outputTensor.copyTo(outputData);
        float[] output = outputTensor.copyTo(new float[1][10])[0];
        int predictedDigit = argmax(output);
        System.out.println("Predicted digit: " + predictedDigit);
        text1.setText(String.valueOf(predictedDigit));
    }
   /* private static int argmax(float[] array) {
        int maxIndex = 0;
        float maxValue = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxIndex = i;
                maxValue = array[i];
            }
        }
        return maxIndex;
    }*/
    public static int argmax(float[] array) {
        int maxIndex = 0;
        double maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxIndex = i;
                maxValue = array[i];
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

}
