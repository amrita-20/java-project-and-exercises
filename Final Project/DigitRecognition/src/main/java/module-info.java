module com.digitrecognition {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.digitrecognition to javafx.fxml;
    exports com.digitrecognition;
}