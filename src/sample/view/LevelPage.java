package sample.view;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class LevelPage extends Parent {

    private int mapWidth;
    private int mapHeight;

    public LevelPage(Stage primaryStage, int width, int height) {

        // the map will be a square of length = height
        this.mapWidth = height;
        this.mapHeight = height;

        // -----------------------------------------------------------
        // set the elements position



        // -----------------------------------------------------------
        // set the elements sizes



        // -----------------------------------------------------------
        // block the window size

        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);

        // -----------------------------------------------------------
        // add all elements to the screen
    }
}
