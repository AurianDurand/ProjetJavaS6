package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.model.MGame;
import sample.view.VGame;

import java.io.IOException;

public class Home_controller {
    private  MGame game;

    public void onPlayPressed(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/game.fxml"));
//            Parent root = loader.load();
//            Game_controller game_controller = loader.getController();
//            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
//            stage.setOnHidden(e->game_controller.quit());
//            stage.setScene(new Scene(root));
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }



        GridPane grid = new GridPane();
        grid.requestFocus();

        int SIZE_X = 10;
        int SIZE_Y = 10;
        ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y];
        Image imga = new Image("sample/wall.png");


        for (int i = 0; i < SIZE_X; i++) { // initialisation de la grille (sans image)
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();

                tab[i][j] = img;
                tab[i][j].setImage(imga);

                grid.add(img, i, j);
            }
        }

        game = new MGame();

        VGame vgame = new VGame(game);
        game.addObserver(vgame);
        game.start();


        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
        stage.setScene(game.getScene());

        StackPane root = new StackPane();
        root.getChildren().add(grid);
        stage.setScene(new Scene(root, stage.getMaxWidth(), stage.getMaxHeight()));
    }

    public void quit() {
        game.stop();
    }

    public void onQuitPressed(ActionEvent event) {
        Platform.exit();
    }
}

