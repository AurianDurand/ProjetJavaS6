package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.HomePage;
import sample.cours1.ModelePacmanSimple;
import sample.cours1.VTerminal;

public class Main extends Application {

    public final int SIZE_X = 1536; // 1920 * 0.8
    public final int SIZE_Y = 864; // 1080 * 0.8

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Pacman");
        Group root = new Group();

        HomePage homePage = new HomePage(primaryStage, SIZE_X, SIZE_Y);
        root.getChildren().add(homePage);

        primaryStage.setScene(new Scene(root, SIZE_X, SIZE_Y));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

        ModelePacmanSimple modele = new ModelePacmanSimple();
        VTerminal vue = new VTerminal(modele);
        modele.addObserver(vue);
        new Thread(modele).start();

        Game game = new Game();
    }
}
