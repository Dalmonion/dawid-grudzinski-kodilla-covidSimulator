package com.kodilla.covid.old;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class IOServices {
    static boolean answer;


    private static boolean confirmBox(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(120);

        Label labelText = new Label();
        labelText.setText(message);
        labelText.setScaleX(1.5);
        labelText.setScaleY(1.5);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox hb = new HBox();
        hb.setPadding(new Insets(10,15,10,10));
        hb.setSpacing(10);
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(yesButton, noButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(hb);
        borderPane.setCenter(labelText);

        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void closeProgram(Stage window) {
        Boolean answer1 = IOServices.confirmBox("Confirm Exit", "Are you sure you want to exit?");
        if(answer1) window.close();
    }




}
