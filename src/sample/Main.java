package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.cours1.ModelePacmanSimple;
import sample.cours1.VTerminal;
import sample.parser.TMXParser;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        ModelePacmanSimple modele = new ModelePacmanSimple();
        VTerminal vue = new VTerminal(modele);
        modele.addObserver(vue);
        new Thread(modele).start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
