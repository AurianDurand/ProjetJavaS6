package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.MGame;

import java.io.IOException;

public class Game_controller {
    private MGame game;

    public void onBackPressed(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/home.fxml"));
            Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();
            game.stop();
            stage.setScene(new Scene(root));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        game.stop();
    }

    @FXML
    public void initialize() {
        game = new MGame();
        game.start();
    }
}
