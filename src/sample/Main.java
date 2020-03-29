package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public final int SIZE_X = 10;
    public final int SIZE_Y = 10;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/home.fxml"));
        Parent root = loader.load();
//        Home_controller controller = loader.getController();
//        primaryStage.setOnHidden(e->controller.quit());
        primaryStage.setTitle("Pac-Man");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

//        ModelePacmanSimple modele = new ModelePacmanSimple();
//        VTerminal vue = new VTerminal(modele);
//        modele.addObserver(vue);
//        new Thread(modele).start();
//
        Game game = new Game();
        game.startLevel();
    }
}
