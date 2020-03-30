package sample.view;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import sample.model.MGame;
import sample.model.MMap;
import sample.model.entities.MBasicEntity;
import sample.view.structure.CWritableImage;
import sample.view.structure.ImageManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class GameVC extends GridPane implements Observer {
    private MGame game;
    private MMap map;
    private ImageManager entitiesImage = new ImageManager();

    private StackPane stackPane = new StackPane();
    public Label label = new Label();
    private ArrayList<HashMap<Point, ImageView>> layers = new ArrayList<>();

    public GameVC(MGame game, Double width, Double height) {
        this.setPrefSize(width, height);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: black;");
        this.add(this.label, 1, 0);
        this.add(this.stackPane, 1, 1);

        this.label.setText("Score : 0");
        this.label.setStyle("-fx-font-size: 40px;-fx-font-weight: bold;-fx-text-fill: WHITE;");
        GridPane.setHalignment(this.label, HPos.CENTER);
        GridPane.setMargin(this.label, new Insets(0, 0, 30, 0));

        this.game = game;
        this.map = game.getMap();

        this.createEntityImage();
        this.createGrid();
        this.fillGrid();
    }

    private void createEntityImage() {
        for(int i = 0; i < this.map.getSize().y; i++) {
            for (int j = 0; j < this.map.getSize().x; j++) {
                ArrayList<MBasicEntity> entities = this.map.getEntitiesByPosition(new Point(j, i));

                if(entities != null) {
                    for(MBasicEntity entity : entities) {
                        this.entitiesImage.add(entity.getTile().getGid(), "res/maps/" + entity.getTile().getSource(), entity.getTile().getCoordTexture(), entity.getTile().getSize());
                    }
                }
            }
        }
    }

    private void createGrid() {
        for(int i = 0; i < this.map.getNbLayer(); i++) {
            GridPane grid = new GridPane();
            grid.requestFocus();
            grid.setAlignment(Pos.CENTER);

            HashMap<Point, ImageView> layer = new HashMap<Point, ImageView>();
            this.layers.add(layer);

            for(int l = 0; l < this.map.getSize().y; l++) {
                for(int m = 0; m < this.map.getSize().x; m++) {
                    ImageView imgView = new ImageView();
                    imgView.setFitWidth(this.map.getTilesize().x);
                    imgView.setFitHeight(this.map.getTilesize().y);
                    imgView.setPreserveRatio(true);

                    layer.put(new Point(m, l), imgView);
                    grid.add(imgView, m, l);
                }
            }
            this.stackPane.getChildren().add(grid);
        }
    }

    private void fillGrid() {
        for(int i = 0; i < this.layers.size(); i++) {
            for (HashMap.Entry<Point, ImageView> entry : this.layers.get(i).entrySet()) {
                //Permet de récupérer une entitée à un point dans un layer d'indice i
                MBasicEntity e = this.map.getEntityByPosition(entry.getKey(), i);
                if(e != null) {
                    CWritableImage frontImg = (CWritableImage)entry.getValue().getImage();
                    int backEntityId = e.getTile().getGid();
                    int frontEntityId = 0;
                    if(frontImg != null) {
                        frontEntityId = frontImg.getGid();
                    }

                    if(frontEntityId != backEntityId) {
                        this.layers.get(i).get(entry.getKey()).setRotate(e.getTile().getRotation());
                        this.layers.get(i).get(entry.getKey()).setImage(this.entitiesImage.getImageOf(backEntityId));
                    }
                }
                else {
                    this.layers.get(i).get(entry.getKey()).setImage(null);
                }
            }
        }
    }

    private void updateGUI() {
        this.label.setText(Integer.toString(this.map.getScore(0)));
    }

    @Override
    public void update(Observable o, Object arg) {
        this.fillGrid();
//        this.updateGUI();
    }
}
