package com.kodilla.covid;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Pierwsze okno"); //tytuł

        window.setOnCloseRequest(event -> {
            event.consume();
            IOServices.closeProgram(window);
        });

        Button button1 = new Button("Close");
        button1.setOnAction(event -> {
            IOServices.closeProgram(window);
        });

        StackPane layout = new StackPane();
        layout.getChildren().addAll(button1);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.show();
    }
}
