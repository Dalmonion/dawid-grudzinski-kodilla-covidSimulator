package com.kodilla.covid.old;

import com.kodilla.covid.Person;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    final static Color textColor = Color.BLACK;

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("Spread simulation of COVID-19 virus"); //tytuł

        // obłużenie wyjścia z programu poprzez naciśnięcie lewym przyciskiem myszy na 'x'
        window.setOnCloseRequest(event -> {
            event.consume();
            IOServices.closeProgram(window);
        });

        // przyciski
        Button startButton = new Button("Start");
        startButton.setPrefSize(100, 20);

        Button stopButton = new Button("Stop");
        stopButton.setPrefSize(100, 20);

        Button resetButton = new Button("Reset");
        resetButton.setPrefSize(100, 20);

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(100, 20);
        exitButton.setOnAction(event -> IOServices.closeProgram(window));


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(12, 12, 12, 12));
        hBox.setSpacing(12);
        hBox.getChildren().addAll(startButton, stopButton, resetButton, exitButton);
        hBox.setAlignment(Pos.CENTER);

        // slidery
        Label slider1 = new Label("Suwak rozmiaru planszy");

        Label slider2 = new Label("Suwak zageszczenia ludzmi");


        //prawdziwy slider
        final Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(1);

        final Label peopleQuantity = new Label("Group size");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(40);

        peopleQuantity.setTextFill(textColor);
        GridPane.setConstraints(peopleQuantity, 0, 1);
        grid.getChildren().add(peopleQuantity);

        GridPane.setConstraints(slider, 1,1);
        grid.getChildren().add(slider);



        //////////////

        // opis pól z danymi
        Label healthyCountLabel = new Label("Healthy people count:");
        GridPane.setConstraints(healthyCountLabel, 0, 0);

        Label sickCountLabel = new Label("Sick people count:");
        GridPane.setConstraints(sickCountLabel, 0, 1);

        Label curedCountLabel = new Label("Cured people:");
        GridPane.setConstraints(curedCountLabel, 0, 2);

        Label deathCountLabel = new Label("Death count:");
        GridPane.setConstraints(deathCountLabel, 0, 3);

        // pola z danymi
        TextField healthyCount = new TextField("HealthyPeopleCount");
        GridPane.setConstraints(healthyCount, 1, 0);

        TextField sickCount = new TextField("SickPeopleCount");
        GridPane.setConstraints(sickCount, 1, 1);

        TextField curedCount = new TextField("CuredPeople");
        GridPane.setConstraints(curedCount, 1, 2);

        TextField deathCount = new TextField("DeathCount");
        GridPane.setConstraints(deathCount, 1, 3);

        // gridpane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(12, 12, 12, 12));
        gridPane.setVgap(15);
        gridPane.setHgap(10);
        gridPane.getChildren().addAll(healthyCountLabel,
                sickCountLabel, curedCountLabel, deathCountLabel,
                healthyCount, sickCount, curedCount, deathCount);


        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.getChildren().addAll(grid, gridPane);
        vBox.setAlignment(Pos.TOP_CENTER);

        // obraz graficzny
        StackPane stackPane = new StackPane();
        Rectangle helpIcon = new Rectangle(350, 400);
        helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#4977A3")),
                new Stop(0.5, Color.web("#B0C6DA")),
                new Stop(1,Color.web("#9CB6CF"))));
        helpIcon.setStroke(Color.web("#D0E6FA"));
        helpIcon.setArcHeight(3.5);
        helpIcon.setArcWidth(3.5);

        Person person = new Person(stackPane);
        person.draw();

        Text helpText = new Text("?");
        helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 100));
        helpText.setFill(Color.WHITE);
        helpText.setStroke(Color.web("#7080A0"));

//        stackPane.getChildren().addAll(helpIcon, helpText);
        stackPane.setAlignment(Pos.CENTER);
        StackPane.setMargin(helpText, new Insets(0, 10, 0, 0));

        // oknow własciwe obrazu graficznegoo
        Pane canvas = new Pane();


        // gotowa scena
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox);
        borderPane.setBottom(hBox);
        borderPane.setCenter(stackPane);



        Scene scene = new Scene(borderPane, 700, 470);
        window.setScene(scene);
        window.show();

    }
}
