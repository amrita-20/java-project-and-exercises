package com.digitrecognition;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String... args){
        /**
         * launch() method is used to launch the JavaFX application.
         * starts the JavaFX application thread, and then calls the start()
         */
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle( "Number Recognizer");
        NumberDrawGUI numberDrawGUI = new NumberDrawGUI();
        primaryStage.setScene(numberDrawGUI.getScene());
        primaryStage.show();
    }
}
