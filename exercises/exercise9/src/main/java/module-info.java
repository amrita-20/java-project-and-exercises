module com.exercise9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.exercise9 to javafx.fxml;
    exports com.exercise9;
}