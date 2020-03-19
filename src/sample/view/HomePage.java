package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class HomePage extends Parent {

    private int width;
    private int height;

    public HomePage(Stage primaryStage, int width, int height) {

        this.width = width;
        this.height = height;

        // -----------------------------------------------------------
        // import the home page image

        URL urlHomePageImage = getClass().getResource("homePageImage.jpeg");
        Image homePageImage = new Image(urlHomePageImage.toExternalForm());
        ImageView homePageImageView = new ImageView(homePageImage);

        // -----------------------------------------------------------
        // declare the buttons

        Button buttonLevel1 = new Button("Level 1");
        Button buttonLevel2 = new Button("Level 2");
        Button buttonQuit = new Button("Quit");

        // -----------------------------------------------------------
        // set the elements position

        homePageImageView.setLayoutX(0);
        homePageImageView.setLayoutY(0);

        buttonLevel1.setLayoutX(1250);
        buttonLevel1.setLayoutY(300);

        buttonLevel2.setLayoutX(1250);
        buttonLevel2.setLayoutY(350);

        buttonQuit.setLayoutX(1250);
        buttonQuit.setLayoutY(400);

        // -----------------------------------------------------------
        // set the elements sizes

        homePageImageView.setFitWidth(width);
        homePageImageView.setFitHeight(height);
        homePageImageView.setPreserveRatio(true);

        buttonLevel1.setPrefSize(200,30);
        buttonLevel2.setPrefSize(200,30);
        buttonQuit.setPrefSize(200,30);

        // -----------------------------------------------------------
        // set actions for the buttons

        buttonLevel1.setOnAction(eventLoadLevel1 -> {
            System.out.println("Start level 1");
            this.loadLevel(primaryStage, width, height);
        });

        buttonLevel2.setOnAction(eventLoadLevel2 -> {
            System.out.println("Start level 2");
            this.loadLevel(primaryStage, width, height);
        });

        buttonQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        // -----------------------------------------------------------
        // block the window size

        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);

        // -----------------------------------------------------------
        // add all elements to the screen

        this.getChildren().add(homePageImageView);

        this.getChildren().add(buttonLevel1);
        this.getChildren().add(buttonLevel2);
        this.getChildren().add(buttonQuit);
    }

    private void loadLevel(Stage primaryStage, int width, int height) {

        // instantiate the new page
        LevelPage levelPage = new LevelPage(primaryStage, width, height);

        // create a new scene with the new page
        Scene newScene = new Scene(levelPage, width, height);

        // load the new scene
        primaryStage.setScene(newScene);
    }
}
