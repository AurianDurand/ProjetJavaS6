package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.cours1.ModelePacmanSimple;
import sample.cours1.VTerminal;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ModelePacmanSimple modele = new ModelePacmanSimple();
        VTerminal vue = new VTerminal(modele);
        modele.addObserver(vue);
        new Thread(modele).start();

        Game game = new Game();

        Parent root = FXMLLoader.load(getClass().getResource("view/home_menu.fxml"));
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

//        primaryStage.setTitle("Pacman");
//        Group root = new Group();
//
//        HomePage homePage = new HomePage(primaryStage, SIZE_X, SIZE_Y);
//        root.getChildren().add(homePage);
//
//        primaryStage.setScene(new Scene(root, SIZE_X, SIZE_Y));
//        primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
