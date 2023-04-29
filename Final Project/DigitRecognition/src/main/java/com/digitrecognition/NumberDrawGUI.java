package com.digitrecognition;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import java.io.File;


public class NumberDrawGUI {
    private Canvas drawingCanvas;
    private GraphicsContext gc;
    private boolean isDrawing = false;
    private int predictedDigit;
    private String tempImagePath = "DigitRecognition\\src\\main\\resources\\image.png";
    private Text predictedDigitText;
    private Button clearButton;
    private Button predictWithSimpleRecognizer;
    private Button predictWithComplexRecognizer;
    private Scene scene;

    NumberDrawGUI(){
        clearButton = new Button("Clear");
        predictWithSimpleRecognizer = new Button("Simple Prediction");
        predictWithComplexRecognizer = new Button("Convoluted Prediction");
        predictedDigitText = new Text();
        buildCanvas();
        createEvents();
        buildScene();
    }

    public Scene getScene() {
        return scene;
    }

    private void createEvents() {
        //Create event handler on mouse pressed
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            gc.moveTo(e.getX(), e.getY());
            isDrawing = true;
        });

        //Create event handler on mouse dragged
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, (e) -> {
            if (isDrawing) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            }
        });

        //Create event handler on mouse release
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, (e) -> {
            isDrawing = false;
        });

        //Create event handler for clear button
        clearButton.setOnAction(event -> {
            gc.beginPath();
            gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
            predictedDigitText.setText("");
        });

        //Call simple prediction model
        predictWithSimpleRecognizer.setOnAction(event -> {
            saveCanvasAsImage(tempImagePath);
            performPrediction("SIMPLE", tempImagePath);
        });

        // call convoluted prediction model
        predictWithComplexRecognizer.setOnAction(event -> {
            saveCanvasAsImage(tempImagePath);
            performPrediction("COMPLEX", tempImagePath);
        });

    }

    private void buildCanvas() {
        drawingCanvas = new Canvas(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(14);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
    }

    private void buildScene() {
        Label labelHeader = new Label();
        labelHeader.setText("Draw a number (0-9) in the box");
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
        root.addRow(0, labelHeader);
        root.addRow(1, pane, predictedDigitText);
        root.addRow(2, predictWithSimpleRecognizer);
        root.addRow(3, predictWithComplexRecognizer);
        root.addRow(4, clearButton);
        scene = new Scene(root, 600, 550);
        scene.getStylesheets().add("/style.css");
    }

    /**
        Get the snapshot of drawn number on canvas
        save snapshot as a png file
     */
    public void saveCanvasAsImage(String imagePath) {
        WritableImage image = drawingCanvas.snapshot(new SnapshotParameters(), null);
        File file = new File(imagePath);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (Exception e) {
            System.out.println("Exception occurred in application: " + e.getMessage());
        }

    }

    /**
     * created method for calling prediction model based on the model type provided
     * @param modelType
     * @param imagePath
     */
    public void performPrediction(String modelType, String imagePath) {
        NumberRecognizer numberRecognizer = NumberRecognizerFactory.getModel(modelType);
        predictedDigit = numberRecognizer.recognizeNumber(imagePath);
        predictedDigitText.setText("Predicted digit: " + predictedDigit);
        predictedDigitText.setFont(Font.font("Verdana", 24));
        predictedDigitText.setFill(Color.BLUE);
    }
}
