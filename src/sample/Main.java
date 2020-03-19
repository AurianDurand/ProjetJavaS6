package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.view.HomePage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        int width = 1536; // 1920 * 0.8
        int height = 864; // 1080 * 0.8

        primaryStage.setTitle("Pacman");
        Group root = new Group();

        HomePage homePage = new HomePage(primaryStage, width, height);
        root.getChildren().add(homePage);

        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

        // default code
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);

        /*ModelePacmanSimple modele = new ModelePacmanSimple();
        VTerminal vue = new VTerminal(modele);
        modele.addObserver(vue);
        new Thread(modele).start();*/

        // test to verify if the map works properly
        //Map map = new Map(10,10);

    }
}
