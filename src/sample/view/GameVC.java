package sample.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.model.MGame;
import sample.model.Map;
import sample.model.entities.BasicEntity;
import sample.view.structure.ImageTemplate;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GameVC extends GridPane implements Observer {
    private MGame game;
    private Map map;
    private ImageTemplate entitiesImage = new ImageTemplate();
    private HashMap<Point, ArrayList<ImageView>> imagesView = new HashMap<Point, ArrayList<ImageView>>();

    private StackPane stackPane = new StackPane();

    public GameVC(MGame game, Double width, Double height) {
        this.setPrefSize(width, height);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: black;");
        this.getChildren().add(this.stackPane);
//        stackPane.setAlignment(Pos.CENTER);

        this.game = game;
        this.map = game.getMap();

        this.createEntityImage();
        this.createGrid();
    }

    private void createEntityImage() {
        for(int i = 0; i < this.map.getSize().y; i++) {
            for (int j = 0; j < this.map.getSize().x; j++) {
                ArrayList<BasicEntity> entities = this.map.getEntitiesByPosition(new Point(j, i));

                if(entities != null) {
                    for(BasicEntity entity : entities) {
                        this.entitiesImage.add(entity.getId(), "res/maps/" + entity.getTile().getSource(), entity.getTile().getCoordTexture(), entity.getTile().getSize());
                    }
                }
            }
        }
    }

    private void createGrid() {
        for(int i = 0; i < this.map.getNbLayer(); i++) {
            GridPane grid = new GridPane();

            for(int l = 0; l < this.map.getSize().y; l++) {
                for(int m = 0; m < this.map.getSize().x; m++) {
                    this.imagesView.computeIfAbsent(new Point(m, l), k -> new ArrayList<ImageView>());
                    ImageView imgView = new ImageView();
                    imgView.setFitWidth(this.map.getTilesize().x);
                    imgView.setFitHeight(this.map.getTilesize().y);
                    imgView.setPreserveRatio(true);
                    this.imagesView.get(new Point(m, l)).add(imgView);
                    grid.add(imgView, m, l);
                }
            }
            grid.requestFocus();
            grid.setAlignment(Pos.CENTER);
            this.stackPane.getChildren().add(grid);
        }
//        this.fillGrid();
    }

    private void fillGrid() {
        for(int i = 0; i < this.map.getSize().y; i++) {
            for (int j = 0; j < this.map.getSize().x; j++) {
                ArrayList<BasicEntity> entities = this.map.getEntitiesByPosition(new Point(j, i));

                if(entities != null) {
                    for(int k = 0; k < entities.size(); k++) {
                        this.imagesView.get(new Point(j, i)).get(k).setImage(this.entitiesImage.getImageOf(entities.get(k).getId()));
                    }
                }
            }
        }
    }

    private void clearGrid() {
        for(int i = 0; i < this.map.getSize().y; i++) {
            for (int j = 0; j < this.map.getSize().x; j++) {
                for(int k = 0; k < this.imagesView.get(new Point(j, i)).size(); k++) {
                    this.imagesView.get(new Point(j, i)).get(k).setImage(null);
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
//        System.out.println("View notified");
        this.clearGrid();
        this.fillGrid();
    }
}
