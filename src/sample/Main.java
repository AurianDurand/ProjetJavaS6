package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.controller.Home_controller;
import sample.model.MGame;
import sample.view.VGame;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class Main extends Application {
    public final int SIZE_X = 10;
    public final int SIZE_Y = 10;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/home.fxml"));
        Parent root = loader.load();
        Home_controller controller = loader.getController();
        primaryStage.setOnHidden(e->controller.quit());
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.out.println("A+");
                Platform.exit();
            }
        });
    }

    public static void main(String[] args) { launch(args); }
}
