package com.digitrecognition;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;


public class NumberDrawGUI extends Application {

    private Canvas drawingCanvas;
    private GraphicsContext gc;
    private boolean isDrawing = false;
    Text predictedDigitText = new Text();
    Button clearButton = new Button("Clear");
    Button predictWithSimpleRecognizer = new Button("Simple Prediction");
    Button predictWithComplexRecognizer = new Button("Convoluted Prediction");
    File file;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Number Recognizer");

        drawingCanvas = new Canvas(200, 200);
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

        //clear the drawn number and predicted values
        clearButton.setOnAction(event -> {
            gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
            predictedDigitText.setText("");
            if(file != null && file.exists()){
                file.delete();
            }
        });

        //Call simple prediction model
        predictWithSimpleRecognizer.setOnAction(event -> {
            getModel("SIMPLE");
        });

        // call convoluted prediction model
        predictWithComplexRecognizer.setOnAction(event -> {
           getModel("COMPLEX");
        });

        GridPane root = new GridPane();
        Pane pane = new Pane();
        pane.getChildren().add(drawingCanvas);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        root.getColumnConstraints().addAll(column1, column2);
        root.setVgap(20);
        root.setHgap(20);
        root.addRow(0, pane, predictedDigitText);
        root.addRow(1, predictWithSimpleRecognizer);
        root.addRow(2, predictWithComplexRecognizer);
        root.addRow(3, clearButton);

        //StackPane root = new StackPane(drawingCanvas, saveButton);
        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
        Get the snapshot of drawn number on canvas
        save snapshot as a png file
        and then call the model based on passed model type
     */
    public void getModel(String modelType) {
        WritableImage image = drawingCanvas.snapshot(new SnapshotParameters(), null);
        PixelReader pixelReader = image.getPixelReader();
        file = new File("image.png");
        try {
            if (isCanvasEmpty(drawingCanvas, pixelReader)) {
                predictedDigitText.setText("Please draw a digit then try");
            }else{
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                NumberRecognizer numberRecognizer = NumberRecognizerFactory.getModel(modelType);
                int predictedDigit = numberRecognizer.recognizeNumber();
                System.out.println("Predicted digit: " + predictedDigit);
                predictedDigitText.setText("Predicted digit: " + predictedDigit);
                predictedDigitText.setFont(Font.font("Verdana", 20));
                predictedDigitText.setFill(Color.BLUE);
            }
//                System.out.println(file.length());
//                BufferedImage bi = ImageIO.read(file);
//                System.out.println("Image width: " + bi.getWidth() + ", height: " + bi.getHeight());

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

    }

    public static boolean isCanvasEmpty(Canvas canvas, PixelReader pixelReader) {
        int width = (int) canvas.getWidth();
        int height = (int) canvas.getHeight();
       // PixelReader pixelReader = canvas.snapshot(null, null).getPixelReader();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixelReader.getColor(x, y).getOpacity() != 0) {
                    return false;
                }
            }
        }
        return true;
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

    public static void main(String[] args) {
        launch(args);
    }

}
