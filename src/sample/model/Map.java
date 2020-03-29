package sample.model;

import javafx.util.Pair;
import sample.model.entities.BasicEntity;
import sample.parser.Layers;
import sample.parser.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Map {
    public enum DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Point size;
    private Point tilesize;
    private int nbLayer;
    private Layers layers;
    private ArrayList<HashMap<Point, BasicEntity>> entityMaps;
    private Stack<DIRECTION> stackInput;

    public Point getSize() { return this.size; }
    public Point getTilesize() { return this.tilesize; }
    public int getNbLayer() { return this.nbLayer; }

    public ArrayList<HashMap<Point, BasicEntity>> getLayers() { return this.entityMaps; }

    public Map() {
        this.entityMaps = new ArrayList<>();
        this.stackInput = new Stack<>();
    }

    public BasicEntity getEntityByPosition(Point point, int idLayer) {
        if(idLayer < this.entityMaps.size()) {
            return this.entityMaps.get(idLayer).get(point);
        }
        return null;
    }

    public ArrayList<BasicEntity> getEntitiesByPosition(Point point) {
        ArrayList<BasicEntity> e = new ArrayList<>();

        for(HashMap<Point, BasicEntity> hashMap : this.entityMaps) {
            if(hashMap.containsKey(point)) {
                e.add(hashMap.get(point));
            }
        }
        return e;
    }

    public void pushInput(DIRECTION key) {
        if(this.stackInput.isEmpty() || (!this.stackInput.peek().equals(key) && this.stackInput.size() < 10)) {
            this.stackInput.push(key);
            System.out.println(stackInput);
        }

        ArrayList<Pair<Pair<Point, Point>, BasicEntity>> entitiesToMove = new ArrayList<>();
        for(HashMap<Point, BasicEntity> hashMap : this.entityMaps) {
            for (HashMap.Entry<Point, BasicEntity> entry : hashMap.entrySet()) {
                if(entry.getValue().getTile().getType().equals("PACMAN")) {
                    Point p = new Point(0, 0);
                    if(key == DIRECTION.UP) {
                        p.y -= 1;
                    }
                    else if(key == DIRECTION.DOWN) {
                        p.y += 1;
                    }
                    else if(key == DIRECTION.LEFT) {
                        p.x -= 1;
                    }
                    else if(key == DIRECTION.RIGHT) {
                        p.x += 1;
                    }
                    Point p1 = entry.getKey();
                    Point p2 = new Point(entry.getKey().x + p.x,entry.getKey().y + p.y);
                    BasicEntity entity = entry.getValue();

                    entitiesToMove.add(new Pair<>(new Pair<>(p1, p2), entity));
                }
            }
            for(Pair<Pair<Point, Point>, BasicEntity> e : entitiesToMove) {
                hashMap.put(e.getKey().getValue(), e.getValue());
                hashMap.remove(e.getKey().getKey());
            }
        }
    }

    public boolean moveEntity(String entityType) {
//        for (HashMap.Entry<Point, ArrayList<BasicEntity>> entry : mEntities.entrySet()) {
//            ArrayList<BasicEntity> myListOfEntity = entry.getValue();
//            for(BasicEntity e : myListOfEntity) {
//                if(e.getTile().getType().equals(entityType)) {
//                    System.out.println(entry.getKey());
//
//                    Point currentPos = entry.getKey();
//                    Point nextPos = new Point(currentPos.x, currentPos.y - 1);
//                    myListOfEntity.remove(e);
//                    this.addEntity(nextPos, e);
//
//                    System.out.println(nextPos);
//                    return true;
//                }
//            }
//        }


        return false;
    }


    private void addEntity(int idLayer, Point pos, BasicEntity entity) {
        if(idLayer < this.entityMaps.size()) {
            this.entityMaps.get(idLayer).put(pos, entity);
        }
    }

    public boolean create(Layers data) {
        //On test si le parser n'a pas d'erreur
        if(data != null) {
            List<Tile[][]> layers = data.getLayers();
            if(layers != null) {
                this.layers = data;
                this.size = new Point(data.getWidth(), data.getHeight());
                this.tilesize = new Point(data.getTilewidth(), data.getTileheight());
                this.nbLayer = layers.size();

                this.entityMaps.clear();

                //On parcourt chaque layer
                for(int k = 0; k < layers.size(); k++) {
                    Tile[][] layer = layers.get(k);
                    this.entityMaps.add(new HashMap<Point, BasicEntity>());
                    //On parcourt chaque case
                    for (int i = 0; i < layer.length; i++) {
                        for (int j = 0; j < layer[i].length; j++) {
                            //si la case est pleine
                            if (layer[i][j] != null) {
                                //Si il n'y en a pas, on n'en créer une. Sinon on ajoute une autre entité
                                this.addEntity(k, new Point(j, i), new BasicEntity(layer[i][j]));
                            }
                        }
                    }
                }
//                this.display();
                return true;
            }
        }

        return false;
    }

    public void display() {
        //On test si le parser n'a pas d'erreur
        if(this.layers != null) {
            List<Tile[][]> layers = this.layers.getLayers();
            if(layers != null){
                System.out.println("<--------------------------------------------------------------->");
                //On parcourt chaque layer
                for(Tile[][] layer : layers){
                    //On parcourt chaque case
                    for (int i = 0; i < layer.length; i++) {
                        for (int j = 0; j < layer[i].length; j++) {
                            if (layer[i][j] != null) {
                                //la case est pleine
                                System.out.print(layer[i][j].getGid());//getter -> Source, taille, posTexture, type
                            } else {
                                //la case est vide
                                System.out.print("0");
                            }
                            System.out.print(", ");
                        }
                        System.out.println("");
                    }
                    System.out.println("");
                }

                System.out.println("Map de taille : " + this.layers.getWidth() + " : " + this.layers.getHeight());
                System.out.println("Tile de taille : " + this.layers.getTilewidth() + " : " + this.layers.getTileheight());
                System.out.println("<--------------------------------------------------------------->");
            }
        }
    }
}
