module application.exercise2_withfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.exercise2_withfx to javafx.fxml;
    exports application.exercise2_withfx;
}