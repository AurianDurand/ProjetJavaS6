package sample.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Game;
import sample.model.MGame;
import sample.model.MMap;
import sample.view.GameVC;

import java.util.ArrayList;

public class Home_controller {
//    private  MGame game;
    private Game game;

    public Home_controller() {
//        Game game = new Game();
//        game.loadLevel("Level 1");
//        game.start();
    }

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

//        game = new MGame();
//        if(!game.initialization()) {
//            System.out.println("Error init game");
//            return;
//        }

        game = new Game();
        game.loadLevel("Level 3");

        Stage stage = (Stage)((Node)(event.getSource())).getScene().getWindow();

        GameVC vgame = new GameVC(game, 1500.0, 900.0);

        StackPane test = new StackPane();
//        Scene gameScene = new Scene(test, 1000, 1000);
        Scene gameScene = new Scene(vgame);

        ArrayList<String> input = new ArrayList<String>();

//        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case Z:
//                        game.getMap().pushInput(MMap.DIRECTION.UP);
//                        break;
//                    case Q:
//                        game.getMap().pushInput(MMap.DIRECTION.LEFT);
//                        break;
//                    case S:
//                        game.getMap().pushInput(MMap.DIRECTION.DOWN);
//                        break;
//                    case D:
//                        game.getMap().pushInput(MMap.DIRECTION.RIGHT);
//                        break;
//                    default:
//                        break;
//                }
//
//                String code = keyEvent.getCode().toString();
//                if(!input.contains(code)) {
//                    input.add(code);
////                    game.getMap().moveEntity("PACMAN");
//                }
//            }
//        });
//        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                String code = keyEvent.getCode().toString();
//                input.remove( code );
//            }
//        });

        stage.setScene(gameScene);
//        stage.show();

        game.addObserver(vgame);
        game.start();
    }

    public void quit() {
        game.stop();
    }

    public void onQuitPressed(ActionEvent event) {
        Platform.exit();
    }
}

