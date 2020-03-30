package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.model.MGame;
import sample.model.MMap;
import sample.view.GameVC;

import java.util.ArrayList;

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

        game = new MGame();
        if(!game.initialization()) {
            System.out.println("Error init game");
            return;
        }

        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();

        GameVC vgame = new GameVC(game, 1500.0, 900.0);
        Scene gameScene = new Scene(vgame);

        ArrayList<String> input = new ArrayList<>();

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
//                        vgame.label.setText(Integer.toString(game.getMap().getScore(0)));
                        game.getMap().setPlayerDirection(MMap.DIRECTION.UP);
                        break;
                    case LEFT:
                        game.getMap().setPlayerDirection(MMap.DIRECTION.LEFT);
                        break;
                    case DOWN:
                        game.getMap().setPlayerDirection(MMap.DIRECTION.DOWN);
                        break;
                    case RIGHT:
                        game.getMap().setPlayerDirection(MMap.DIRECTION.RIGHT);
                        break;
                    default:
                        break;
                }

                String code = keyEvent.getCode().toString();
                if(!input.contains(code)) {
                    input.add(code);
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String code = keyEvent.getCode().toString();
                input.remove( code );
            }
        });

        game.addObserver(vgame);
        game.start();

        stage.setScene(gameScene);
    }

    public void quit() {
        game.stop();
    }

    public void onQuitPressed(ActionEvent event) {
        Platform.exit();
    }
}

