package application.exercise2_withfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShapeFX extends Application {
    double height;
    double width;
    double radius;
    Double [] points;

    //UI components
    Label label =new Label("Select Shape");
    Text text1 = new Text();
    Text text2 = new Text();
    ComboBox<String> comboBox = new ComboBox<>();
    Button draw = new Button("Draw");
    Button calculateArea = new Button("Calculate Area");
    Button calculatePerimeter = new Button("Calculate Perimeter");
    Group group = new Group(); //creating Group

    @Override
    public void start(Stage stage) throws Exception {
        comboBox.getItems().addAll("Rectangle", "Circle", "Square", "Right Triangle");
        comboBox.setValue("Rectangle");

        comboBox.setOnAction(e -> {
            text2.setText("");
            text1.setText("");
            group.getChildren().clear();
        });

        draw.setOnAction(e -> {
            handleDraw();
        });

        calculateArea.setOnAction(e -> {
            handleCalculation("Area");
        });

        calculatePerimeter.setOnAction(e -> {
            handleCalculation("Perimeter");
        });

        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column2.setPercentWidth(10);
        ColumnConstraints column4 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column2.setPercentWidth(20);
        root.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
        // root.setAlignment(Pos.TOP_CENTER);
        root.setVgap(20);
        root.setHgap(20);
        root.addRow(0, label, comboBox, draw, calculateArea, calculatePerimeter);
        root.addRow(1, group);
        root.addRow(2, text1);
        root.addRow(3, text2);

        Scene scene=new Scene(root,800,600);
        scene.getStylesheets().add("/shapeStyle.css");
        stage.setScene(scene);
        stage.setTitle("Shapes");
        stage.show();
    }

    //Created method to handle draw functionality
    public void handleDraw(){
        String shapeName = comboBox.getValue();
        if(shapeName.equalsIgnoreCase("Rectangle")){
            Rectangle rect=new Rectangle(); //instantiating Rectangle
            rect.setX(20); //setting the X coordinate of upper left //corner of rectangle
            rect.setY(20); //setting the Y coordinate of upper left //corner of rectangle
            rect.setWidth(200); //setting the width of rectangle
            rect.setHeight(100); // setting the height of rectangle
            rect.setStroke(Color.BLACK);
            height = rect.getHeight();
            width = rect.getWidth();
            rect.setFill(Shape.color);
            group.getChildren().add(rect);
        }else if(shapeName.equalsIgnoreCase("Circle")){
            Circle circle = new Circle();
            circle.setCenterX(100);
            circle.setCenterY(100);
            circle.setRadius(50.5);
            circle.setStroke(Color.BLACK);
            radius = circle.getRadius();
            circle.setFill(Shape.color);
            group.getChildren().add(circle);
        }
        else if(shapeName.equalsIgnoreCase("Square")) {
            Rectangle rect = new Rectangle(); //instantiating Rectangle
            rect.setX(20); //setting the X coordinate of upper left //corner of rectangle
            rect.setY(20); //setting the Y coordinate of upper left //corner of rectangle
            rect.setWidth(100); //setting the width of rectangle
            rect.setHeight(100); // setting the height of rectangle
            rect.setStroke(Color.BLACK);
            height = rect.getHeight();
            width = rect.getWidth();
            rect.setFill(Shape.color);
            group.getChildren().add(rect);
        }
        else if(shapeName.equalsIgnoreCase("Right Triangle")) {
            points = new Double[]{0.0, 0.0, 0.0, 100.0, 120.0, 0.0};
            Polygon polygon = new Polygon();
            polygon.getPoints().addAll(points);
            height = Math.abs(points[3] - points[1]);
            width = Math.abs(points[4] - points[0]);
            polygon.setStroke(Color.BLACK);
            polygon.setFill(Shape.color);
            group.getChildren().add(polygon);
        }
    }

    //Create instance of each class
    public void handleCalculation(String calculationType){
        String shapeType = comboBox.getValue();
        Shape sh;
        switch (shapeType.toUpperCase()){
            case "RECTANGLE":
                sh = new ShapeRectangle(height, width);
                break;
            case "SQUARE":
                sh = new ShapeSquare(height);
                break;
            case  "CIRCLE":
                sh = new ShapeCircle(radius);
                break;
            case  "RIGHT TRIANGLE":
                sh = new ShapeTriangle(height, width);
                break;
            default:
                sh = null;
        }
        if(calculationType.equalsIgnoreCase("Perimeter"))
            text2.setText("Perimeter of " + shapeType + ": \n" + sh.calculatePerimeter());
        else
            text1.setText("Area of " + shapeType + ": \n" + sh.calculateArea());
    }

    public static void main(String... args){
        launch(args);
    }
}

