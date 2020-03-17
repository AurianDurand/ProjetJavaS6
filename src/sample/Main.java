package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.cours1.ModelePacmanSimple;
import sample.cours1.VTerminal;
import sample.map.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        // launch(args);

        ModelePacmanSimple modele = new ModelePacmanSimple();
        VTerminal vue = new VTerminal(modele);
        modele.addObserver(vue);
        new Thread(modele).start();

        Map map = new Map(10,10);

        System.out.println(map.isTheTileAPath(1,3));

    }
}
