module com.digitrecognition {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires libtensorflow;
    requires javafx.swing;


    opens com.digitrecognition to javafx.fxml;
    exports com.digitrecognition;
}